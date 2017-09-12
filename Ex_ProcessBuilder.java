import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex_ProcessBuilder {
  public static void main(final String[] args) throws IOException, InterruptedException {
    List<String> commands = new ArrayList<String>();
    commands.add("/bin/cat");
    String pwd = System.getenv("PWD");  // get current working directory
    commands.add(pwd + "/Ex_ProcessBuilder_Data.txt");  // path to data file
    System.out.println(commands);

    // Build and start process
    ProcessBuilder pb = new ProcessBuilder(commands);
    pb.directory(new File(pwd));  // set working directory where process p would execute from
    pb.redirectErrorStream(true);
    Process process = pb.start();

    // Set up to receive the output of the process as input to BufferedReader
    StringBuilder out = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line = null, previous = null;
    while ((line = br.readLine()) != null)
      if (!line.equals(previous)) {
        previous = line;
        out.append(line).append('\n');
        System.out.println(line);
      }

    //Check result
    if (process.waitFor() == 0) {
      System.out.println("Success!");
      System.exit(0);
    }

    //Abnormal termination: Log command parameters and output and throw ExecutionException
    System.err.println(commands);
    System.err.println(out.toString());
    System.exit(1);
  }
}

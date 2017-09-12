/*  Every Java application has a single instance of class Runtime that allows 
    the application to interface with the environment in which the application is 
    running. The current runtime can be obtained from the getRuntime method.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Shell_cmd_in_Java {
  public static void main(String args[]) {
    String s;
    Process p;
    try {
      p = Runtime.getRuntime().exec("ls -aF");

      // Feed BufferedReader with an InputStream,
      // in this case, the output of subprocess p
      BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));
      while ((s = br.readLine()) != null) {
        System.out.println("line: " + s);
      }
      p.waitFor();    // wait for asy/nchronous subprocess to end
      System.out.println ("exit: " + p.exitValue());    // 0 is success
      p.destroy();    // kill subprocess
    } catch (Exception e) {}
  }
}

public class Shell_env_vars {
  public static void main(String[] args) {
    String user = System.getenv("USER");
    String path = System.getenv("PATH");
    System.out.println("USER: " + user);
    System.out.println("PATH: " + path);
  }
}

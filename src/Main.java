import CPU.Registers;
public class Main  {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Registers r = new Registers();
        String val=r.get_register_value_string("R0");
        System.out.println(val);
        System.out.println("hi");
    }
}
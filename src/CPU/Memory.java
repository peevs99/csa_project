package CPU;
import java.util.Arrays;
import java.util.List;

public class Memory
{

    int size = 2048;
    List<Integer> ProcessorMemory = Arrays.asList(new Integer[size]);


    public void modify_memory(String modification)
    {
        if (modification.equals("expand") && ProcessorMemory.size() == 2048)
        {
            List<Integer> ProcessorMemoryTemp = Arrays.asList(new Integer[4096]);
            for(int i=0; i<ProcessorMemory.size(); i++)
            {
                ProcessorMemoryTemp.set(i, ProcessorMemory.get(i));
            }
            ProcessorMemory = ProcessorMemoryTemp;
        }
        else if (modification.equals("contract") && ProcessorMemory.size() == 4096)
        {
            List<Integer> ProcessorMemoryTemp = Arrays.asList(new Integer[2048]);
            for(int i=0; i<ProcessorMemory.size(); i++)
            {
                ProcessorMemoryTemp.set(i, ProcessorMemory.get(i));
            }
            ProcessorMemory = ProcessorMemoryTemp;
        }
    }

    public int get_from_memory_int(int addr)
    {
        return ProcessorMemory.get(addr);
    }

    public String get_from_memory_string(int addr)
    {
        return String.format("%16s", Integer.toBinaryString(ProcessorMemory.get(addr))).replace(' ', '0');
    }

    public void store_to_memory(int addr, int val)
    {
        ProcessorMemory.set(addr, val);
    }

}

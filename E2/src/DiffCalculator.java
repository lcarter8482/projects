import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiffCalculator
{
    private List<IDiffAlg> m_diffAlgs;

    public static void main(String[] args) throws IOException
    {
        DiffCalculator main = new DiffCalculator();
        main.go("Sequences.txt");
    }

    public DiffCalculator()
    {
        m_diffAlgs = new ArrayList<IDiffAlg>();
        m_diffAlgs.add(OrderAlg.getInstance());
        m_diffAlgs.add(WeightedOrderAlg.getInstance());
        m_diffAlgs.add(CountAlg.getInstance());

    }

    public void go(String filename) throws IOException
    {
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while (line != null)
            {
                if (line.length() > 0 && !line.startsWith("#"))
                {
                    String tokens[] = line.split(",");
                    if (tokens.length == 2)
                    {
                        double total = 0.0;
                        for (IDiffAlg alg : m_diffAlgs)
                        {
                            int diff = alg.difference(tokens[0], tokens[1]);
                            total = total + diff;
                        }
                        double ave = total/m_diffAlgs.size();
                        System.out.printf("Average for %s-%s = %.2f%n", tokens[0], tokens[1], ave);
                    }
                    else
                    {
                        throw new IllegalStateException("Incorrect number of sequences on a line.");
                    }
                }
                line = in.readLine();
            }
        }
        finally
        {
            if (in != null)
                in.close();
        }
    }
}


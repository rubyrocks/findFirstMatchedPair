import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class findFirstMatchedPair {
    
    private static String findFirstMatchedPair(String strVal)
    {
        String ret = "";
        LinkedHashMap<String, Integer> characterCount = new LinkedHashMap<String, Integer>();


        String c = "";
        String strkey = "";

        for ( int i = 0; i < strVal.length(); i++ )
        {

            c =  strVal.charAt(i)+"";
            strkey = c.toLowerCase();

            if (! characterCount.containsKey(strkey))
            {

                characterCount.put(strkey, new Integer(1));
            }

            else
            {
                int value = characterCount.get(strkey) + 1;
                characterCount.put(strkey, new Integer(value));
            }

        }

        for (Map.Entry<String, Integer> rec : characterCount.entrySet() )
        {

            if ( rec.getValue() >= 2 )
            {
                ret = rec.getKey();
                break;
            }

        }

        return ret;

    }


    public static String findFirstMatchedPairRefactored(String strVal)
    {

        String ret = "";

        Map<String, AtomicInteger> map = new LinkedHashMap<>();
        strVal.toLowerCase().codePoints().mapToObj(Character::toChars).map(String::valueOf).forEachOrdered(s -> map.computeIfAbsent(s, k -> new AtomicInteger()).incrementAndGet());
        ret = map.entrySet().stream().filter(e -> e.getValue().intValue() > 1).map(Map.Entry::getKey).findFirst().get();

        return ret;
    }


    public static void main (String[] args)
    {
        String strTestData[] = {"Hello World", "Google"};

        for (String strVal : strTestData)
        {
            System.out.println("Old School...");
            System.out.println(findFirstMatchedPair(strVal));
            System.out.println("Refactored....");
            System.out.println(findFirstMatchedPairRefactored(strVal));
        }
    }
}

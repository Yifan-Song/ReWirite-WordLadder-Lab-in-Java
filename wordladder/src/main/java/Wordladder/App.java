package Wordladder;
import java.io.*;
import java.util.*;

public class App 
{
    public static String ToStandardString(String str)
    {
        str = str.toLowerCase();
        str = str.replaceAll(" ","");
        return str;
    }

    public static boolean IsWord(String s,Set<String> dictionary)
    {
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if((int)ch > 122 || (int)ch <97)return false;
        }
        if (dictionary.contains(s))return true;
        return false;
    }

    public static boolean Search(String start, String end, Set<String> dictionary)
    {
        boolean same = false;
        Set<String> used = new HashSet<String>();
        String newWord = "";
        Stack<String> s1 = new Stack<String>();
        Queue<Stack<String>> q = new LinkedList<Stack<String>>();
        s1.push(start);
        q.add(s1);
        while (!q.isEmpty())
        {
            Stack<String> s = q.peek();
            q.remove();
            String top = s.peek();
            for (int i = 0;i < top.length() + 1;i++)
            {
                for (int j = 97;j < 149;j++)
                {
                    if (j >= 123)
                    {
                        char ch = (char)(j - 26);
                        if (top.length() < end.length())
                        {
                            newWord = top.substring(0, i) + ch +top.substring(i, top.length());
                        }
                        if (top.length() > end.length())
                        {
                            if (i == top.length())continue;
                            newWord = top.substring(0, i) + top.substring(i + 1, top.length());
                        }
                    }
                    else
                    {
                        if (i == top.length())continue;
                        char ch = (char)(j);
                        newWord = top.substring(0, i) + ch + top.substring(i + 1, top.length());
                    }
                    if (used.contains(newWord))same = true;
                    if (same)
                    {
                        same = false;
                        continue;
                    }
                    if (!newWord.equals(end)&&!IsWord(newWord, dictionary))continue;
                    if (newWord.equals(end))
                    {
                        s.push(newWord);
                        int size = s.size();
                        System.out.println("A ladder from "+end+" back to "+start+":\n");
                        for (int k = 0;k < size;k++)
                        {
                            System.out.println(s.peek());
                            s.pop();
                        }
                        System.out.println("\n\n");
                        return true;
                    }
                    else
                    {
                        used.add(newWord);
                        Stack<String> newStack = (Stack<String>) s.clone();
                        newStack.push(newWord);
                        q.add(newStack);
                    }
                }
            }
        }
        System.out.println("No word ladder found from "+end+" back to "+start+":\n\n");
        return false;
    }

    public static void main(String[] args ) {
        Set<String> dictionary = new HashSet<String>();
        String filename;
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            System.out.println("Dictionary file name? ");
            filename = sc.nextLine();
            filename = "res\\" + filename;

            File file=new File(filename);
            if(!file.exists()||file.isDirectory()) {
                System.out.println("Unable to open that file.  Try again.\n");
                continue;
            }
            try {
                FileInputStream out = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(out));
                String s1 = null;
                while ((s1 = br.readLine()) != null) {
                    dictionary.add(s1);
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            break;
        }
        while (true)
        {
            String Start;
            String End;
            System.out.println("\nWord #1 (or Enter to quit):");
            Start = sc.nextLine();
            Start = ToStandardString(Start);
            if (Start.equals(""))break;
            System.out.println("Word #2 (or Enter to quit):");
            End = sc.nextLine();
            End = ToStandardString(End);
            if (End.equals(""))break;
            if (End.equals(Start))
            {
                System.out.println("The two words must be different.\n\n");
                continue;
            }
            Search(Start, End, dictionary);
        }
        System.out.println( "Have a nice day.\n" );
    }
}

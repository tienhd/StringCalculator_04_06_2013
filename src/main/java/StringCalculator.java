/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/4/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCalculator {

    public int result = 0;
    public int Add (String inputString) {
        if (inputString.isEmpty()) {
            return result;
        }
        else {
            String regex = "[,;\\n//*#]";
            String negative = "-([\\d])(.*)";
            String define = "(//)(\\[)(.*)(\\])(\\n)(.*)";

            String negativeNumbers = "";
            boolean ok = false;

            if (inputString.matches(define)) {
                regex = getMultiDefineDelimiter(inputString);
                String tempString = regex;
                String[] getString = inputString.split("\\n");
                inputString = getString[1];
            }
            String[] inputNumbers = inputString.split(regex);
            for (String si : inputNumbers) {
                if (si.matches(negative)) {
                    ok = true;
                    negativeNumbers += si + " ";
                    continue;
                }
                if (ok) {
                    throw new IllegalArgumentException("Negative is not allowed. " + negativeNumbers );
                }
                if ((!si.isEmpty()) && (!si.equals("[")) && (!si.equals("]"))) {
                    int number = Integer.parseInt(si);
                    if (number <= 1000) {
                        result += number;
                    }

                }
            }
            return result;
        }
    }

    public String getMultiDefineDelimiter(String inputString) {
        String result = "";
        result += "[";
        String getPattern = "(//)(.*)(\\n)(.*)";
        if (inputString.matches(getPattern)) {
//            System.out.println("MATCH MULTI");
            String[] temp = inputString.split("\\n");
            String[] preDefine = temp[0].split("//");
            String tempDelimiter = preDefine[1];
//            System.out.println(tempDelimiter);
            String pattern = "(\\[)(.*)(\\])(.*)";
            if (tempDelimiter.matches(pattern)) {
                tempDelimiter = tempDelimiter.replaceAll("\\["," ");
                tempDelimiter = tempDelimiter.replaceAll("\\]"," ");
                String[] delimiter = tempDelimiter.split(" ");
                for (String di: delimiter) {
                    if (!di.isEmpty()) {
                        result += "(" + di + ")";
                    }
                }
            }
            result += "]";
        }
        return result;
    }
}

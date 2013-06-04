import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/4/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCalculatorTest {
    @Test
    public void TestWithNoInputNumber() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(0, stringCalculator.Add(""));
    }

    @Test
    public void TestWithTwoInputNumber () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(3,stringCalculator.Add("1,2"));
    }

    @Test
    public void TestWithMoreInputNumber () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("1,3,2"));
    }

    @Test
    public void TestEndlineInInputString () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6,stringCalculator.Add("1,2\n3"));
    }

    @Test
    public void TestWithDifferentDelimiter () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6,stringCalculator.Add("//;\n1;2;3"));
    }

    @Test
    public void TestWithDifferentDelimiterAdvance() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6,stringCalculator.Add("//#\n1#2#3"));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void TestNegativeExceptionInInputString() {
        StringCalculator stringCalculator = new StringCalculator();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negative is not allowed. -1");
        stringCalculator.Add("//;\n,-1;2,3");
    }

    @Test
    public void TestWithInputNumberOverThanOneThousand () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(5,stringCalculator.Add("3,2;1001"));
    }

    @Test
    public void TestWithDefineStringDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("//[***]\n1***2***3"));
    }

//    @Test
//    public void TestGetDefineDelimiterModule() {
//        StringCalculator scModule = new StringCalculator();
//        Assert.assertEquals("***",scModule.getDefineDelimiter("//[***]\n1***2***3)"));
//    }

    @Test
    public void TestGetMultiDefineDelimiterModule() {
        StringCalculator scModule = new StringCalculator();
        Assert.assertEquals("[(***)(;)]",scModule.getMultiDefineDelimiter("//[***][;]\n1***2;3"));
    }

    @Test
    public void TestWithMultiDefineDelimiter(){
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("//[***][;]\n1***2;3"));
    }

    @Test
    public void TestWithMultiDefineDelimiterWithLongerLength() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("//[***][;;;]\n1***2;;;3"));
    }
}

package gromecode.lesson31.hw;

public class Demo {
    public static void main(String[] args) {

        Solution solution = new Solution();

        System.out.println(solution.countSymbols("text text text"));
        System.out.println(solution.countSymbols("124 text ext"));
        System.out.println(solution.countSymbols("/xs*cd ivaKLC ext"));
        System.out.println(solution.countSymbols("Иванов иван Иванович"));
        System.out.println(solution.countSymbols("/xs*cd Петро Петрович"));

        System.out.println();

        System.out.println(solution.words("text"));
        System.out.println(solution.words("texttext"));
        System.out.println(solution.words("text text"));
        System.out.println(solution.words("text text text"));
        System.out.println(solution.words("text texttext"));
        System.out.println(solution.words("text text texttext texttext"));
        System.out.println(solution.words("text text texttext textte1xt"));
        System.out.println(solution.words("tex/t *text te3xtt3ext -texttext"));


    }
}

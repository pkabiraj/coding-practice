package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        List<IFibonacci> fibonaccis = new ArrayList<>();
        fibonaccis.add(fib.new FibonacciWithRecursion());
        fibonaccis.add(fib.new FibonacciWithoutRecursion());

        for (IFibonacci fibonacci : fibonaccis) {
            fibonacci.findFibonacci(10);
        }
    }

    private interface IFibonacci {

        void findFibonacci(long no);
    }

    private class FibonacciWithoutRecursion implements IFibonacci {

        @Override
        public void findFibonacci(long no) {
            System.out.println("Fibonacci without recursion...");

            int n1 = 1, n2 = 1;
            System.out.print(n1 + " ");
            System.out.print(n2);

            for (int i = 2; i < no; i++) {
                n2 = n2 + n1;
                n1 = n2 - n1;
                System.out.print(" " + n2);
            }
        }
    }

    private class FibonacciWithRecursion implements IFibonacci {

        @Override
        public void findFibonacci(long no) {
            System.out.println("Fibonacci with recursion...");

            for (int i = 1; i <= no; i++) {
                System.out.print(fibnacci(i) + " ");
            }
            System.out.println();
        }

        private long fibnacci(long fibNo) {
            if (fibNo <= 2) {
                return 1;
            } else {
                return fibnacci(fibNo - 1) + fibnacci(fibNo - 2);
            }
        }
    }
}

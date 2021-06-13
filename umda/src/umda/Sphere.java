/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umda;

/**
 *
 * @author fernando-dlz
 */
    class Sphere implements objectiveFunction {
        // objective function
        @Override
        public double function(double[] x) {
            double total = 0.0;
            for (double xi : x)
                total += Math.pow(xi, 2.0);
            return total;
        }
    }
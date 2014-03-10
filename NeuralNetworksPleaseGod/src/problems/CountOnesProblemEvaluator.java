package problems;

import static evaluator.RandomizedOptimizationEvaluator.GENETIC_ALGORITHM;
import static evaluator.RandomizedOptimizationEvaluator.MIMIC;
import static evaluator.RandomizedOptimizationEvaluator.RANDOM_HILL_CLIMBING;
import static evaluator.RandomizedOptimizationEvaluator.SIMULATED_ANNEALING;

import java.util.Arrays;

import dist.DiscreteUniformDistribution;
import dist.Distribution;
import evaluator.RandomizedOptimizationEvaluator;

import opt.EvaluationFunction;
import opt.example.CountOnesEvaluationFunction;

/**
 * 
 * @author akshata
 *
 */
public class CountOnesProblemEvaluator {

    private static final int N = 80;
    public static void main(String[] arg)
    {
        int[] ranges = new int[N];
        Arrays.fill(ranges, 2);
        
        EvaluationFunction evaluationFunction = new CountOnesEvaluationFunction();
        Distribution distribution = new DiscreteUniformDistribution(ranges);
        
        double optimalValue = 0.0;
        
        RandomizedOptimizationEvaluator evaluator = new RandomizedOptimizationEvaluator();
        
        optimalValue = evaluator.getOptimizedValue(RANDOM_HILL_CLIMBING, evaluationFunction, distribution, ranges);
     
        System.out.println("\nRandom Hill Climbing: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(SIMULATED_ANNEALING, evaluationFunction, distribution, ranges);
        
        System.out.println("\nSimulated Annealing: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(GENETIC_ALGORITHM, evaluationFunction, distribution, ranges);
        
        System.out.println("\nGenetic Algorithms: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(MIMIC, evaluationFunction, distribution, ranges);
        
        System.out.println("\nMIMIC: "+ optimalValue);
    }
  
}

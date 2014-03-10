package problems;

import java.util.Arrays;
import java.util.Random;

import dist.DiscreteUniformDistribution;
import dist.Distribution;
import evaluator.RandomizedOptimizationEvaluator;

import opt.EvaluationFunction;
import opt.example.KnapsackEvaluationFunction;

import static evaluator.RandomizedOptimizationEvaluator.*;

public class KnapsackProblemEvaluator{
	
	private static final int ITEM_COPIES = 6;
	private static final int NUMBER_OF_ITEMS = 10;
	private static final int MAX_WEIGHT = 30;
	private static final int MAX_VOLUME = 30;
	
    private static final double KNAPSACK_VOLUME = 
            MAX_VOLUME * NUMBER_OF_ITEMS * ITEM_COPIES * .4; // Restrict the volume
    
    
    public static void main(String[] args)
    {
    	Random random = new Random();
        int[] copies = new int[NUMBER_OF_ITEMS];
        
        Arrays.fill(copies, ITEM_COPIES);
        
        double[] weights = new double[NUMBER_OF_ITEMS];
        double[] volumes = new double[NUMBER_OF_ITEMS];
        
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            weights[i] = random.nextDouble() * MAX_WEIGHT;
            volumes[i] = random.nextDouble() * MAX_VOLUME;
        }
        
        int[] ranges = new int[NUMBER_OF_ITEMS];
        Arrays.fill(ranges, ITEM_COPIES + 1);
        
        EvaluationFunction evaluationFunction = new KnapsackEvaluationFunction(weights, volumes, KNAPSACK_VOLUME, copies);
        Distribution distribution = new DiscreteUniformDistribution(ranges);
 
        double optimalValue = 0.0;
        
        RandomizedOptimizationEvaluator evaluator = new RandomizedOptimizationEvaluator();
        
        optimalValue = evaluator.getOptimizedValue(RANDOM_HILL_CLIMBING, evaluationFunction, distribution, ranges);
     
        System.out.println("\nOptimal Value: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(SIMULATED_ANNEALING, evaluationFunction, distribution, ranges);
        
        System.out.println("\nOptimal Value: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(GENETIC_ALGORITHM, evaluationFunction, distribution, ranges);
        
        System.out.println("\nOptimal Value: " + optimalValue);
     
        optimalValue = evaluator.getOptimizedValue(MIMIC, evaluationFunction, distribution, ranges);
        
        System.out.println("\nOptimal Value: " + optimalValue);
     
        
    }

    
    
    
}

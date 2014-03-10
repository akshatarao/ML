package evaluator;

import dist.Distribution;
import opt.EvaluationFunction;

public class RandomizedOptimizationEvaluator {

	public static final String RANDOM_HILL_CLIMBING = "RANDOMIZED_OPTIMIZATION";
	public static final String GENETIC_ALGORITHM = "GENETIC_ALGORITHM";
	public static final String SIMULATED_ANNEALING = "SIMULATED_ANNEALING";
	public static final String MIMIC = "MIMIC";
	public static final int NUMBER_OF_ITERATIONS = 2000;
    IRandomizedOptimizationEvaluator evaluator;
	
	public double getOptimizedValue(String randomizedOptimization, EvaluationFunction evaluationFunction, Distribution distribution, int[] ranges)
	{
		double optimalValue = 0.0;
		
		if(RANDOM_HILL_CLIMBING.equalsIgnoreCase(randomizedOptimization))
		{
			evaluator = new RandomHillClimbingEvaluator();
		}
		else if(SIMULATED_ANNEALING.equalsIgnoreCase(randomizedOptimization))
		{
			evaluator = new SimulatedAnnealingEvaluator();
		}
		else if(GENETIC_ALGORITHM.equalsIgnoreCase(randomizedOptimization))
		{
			evaluator = new GeneticAlgorithmEvaluator();
		}
		else if(MIMIC.equalsIgnoreCase(randomizedOptimization))
		{
			evaluator = new MIMICEvaluator();
		}

	    optimalValue = evaluator.getOptimalValue(evaluationFunction, distribution, ranges, 100);

	    return optimalValue;
	}
	
}

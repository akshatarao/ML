package evaluator;

import opt.DiscreteChangeOneNeighbor;
import opt.EvaluationFunction;
import opt.GenericHillClimbingProblem;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.RandomizedHillClimbing;
import shared.FixedIterationTrainer;
import dist.Distribution;

public class RandomHillClimbingEvaluator implements IRandomizedOptimizationEvaluator{

	  /**
     * Get Optimal Volume Using Random Hill Climbing
     * @param evaluationFunction
     * @param distribution
     * @param neighborFunction
     * @return optimalValue
     */
    public double getOptimalValue(EvaluationFunction evaluationFunction, Distribution distribution, int[] ranges, int numberOfIterations)
    {
    	System.out.println("\n------------------------");
    	System.out.println("\nRandomized Hill Climbing");
    	System.out.println("\n------------------------");
    	System.out.println("\nNumber of iterations: " + numberOfIterations);
    	
    	double optimalVolume;
    	NeighborFunction neighborFunction = new DiscreteChangeOneNeighbor(ranges);
    	
    	HillClimbingProblem hcp = new GenericHillClimbingProblem(evaluationFunction, distribution, neighborFunction);
    	
    	RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);      
        
        long startTime = System.currentTimeMillis();
    	FixedIterationTrainer fit = new FixedIterationTrainer(rhc, numberOfIterations);
        fit.train();
        long endTime = System.currentTimeMillis();
        
        System.out.println("\nRunning time: " + (endTime - startTime));
        
        
        optimalVolume = evaluationFunction.value(rhc.getOptimal());
        
        return optimalVolume;
    }
  
}

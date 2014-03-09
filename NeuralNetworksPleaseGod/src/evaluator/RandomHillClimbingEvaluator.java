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
    	double optimalVolume;
    	NeighborFunction neighborFunction = new DiscreteChangeOneNeighbor(ranges);
    	
    	HillClimbingProblem hcp = new GenericHillClimbingProblem(evaluationFunction, distribution, neighborFunction);
    	
    	RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);      
        FixedIterationTrainer fit = new FixedIterationTrainer(rhc, numberOfIterations);
        fit.train();
        
        optimalVolume = evaluationFunction.value(rhc.getOptimal());
        
        return optimalVolume;
    }
  
}

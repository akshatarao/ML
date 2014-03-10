package evaluator;

import opt.DiscreteChangeOneNeighbor;
import opt.EvaluationFunction;
import opt.GenericHillClimbingProblem;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.SimulatedAnnealing;
import shared.FixedIterationTrainer;
import dist.Distribution;

public class SimulatedAnnealingEvaluator implements IRandomizedOptimizationEvaluator{

	double startingTemperature = 100;
	double coolingExponent = 0.95;
	

	  /**
     * Get Optimal Volume Using Simulated Annealing
     * @param evaluationFunction
     * @param distribution
     * @param neighborFunction
     * @return optimalValue
     */
    public double getOptimalValue(EvaluationFunction evaluationFunction, Distribution distribution, int[] ranges, int numberOfIterations)
    {
    	double optimalVolume;

    	System.out.println("\n-------------------");
    	System.out.println("\nSimulated Annealing");
    	System.out.println("\n-------------------");
    	System.out.println("\nNumber of Iterations: " + numberOfIterations);
    	
    	NeighborFunction neighborFunction = new DiscreteChangeOneNeighbor(ranges);
    	
    	HillClimbingProblem hcp = new GenericHillClimbingProblem(evaluationFunction, distribution, neighborFunction);
    	SimulatedAnnealing sa = new SimulatedAnnealing(startingTemperature, coolingExponent, hcp);
    	FixedIterationTrainer fit = new FixedIterationTrainer(sa, numberOfIterations);
 
    	long startTime = System.currentTimeMillis();
    	fit.train();
        long endTime = System.currentTimeMillis();

        System.out.println("\nRunning time: " + (endTime - startTime));

    	optimalVolume = evaluationFunction.value(sa.getOptimal());
        
        return optimalVolume;

    }
}

package evaluator;

import opt.EvaluationFunction;
import opt.ga.CrossoverFunction;
import opt.ga.DiscreteChangeOneMutation;
import opt.ga.GenericGeneticAlgorithmProblem;
import opt.ga.GeneticAlgorithmProblem;
import opt.ga.MutationFunction;
import opt.ga.StandardGeneticAlgorithm;
import opt.ga.UniformCrossOver;
import shared.FixedIterationTrainer;
import dist.Distribution;

public class GeneticAlgorithmEvaluator implements IRandomizedOptimizationEvaluator{

	private static final int populationSize = 200;
	private static final int toMate = 150;
	private static final int toMutate = 25;
	
	@Override
	public double getOptimalValue(EvaluationFunction evaluationFunction,
			Distribution distribution, int[] ranges, int numberOfIterations) {
		
		double optimalValue;

		System.out.println("\n-------------------");
	  	System.out.println("\nGenetic Algorithm");
    	System.out.println("\n-------------------");
    	System.out.println("\nNumber of Iterations: " + numberOfIterations);
  
	    MutationFunction mf = new DiscreteChangeOneMutation(ranges);
        CrossoverFunction cf = new UniformCrossOver();
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(evaluationFunction, distribution, mf, cf);
        
        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(populationSize, toMate, toMutate, gap);
        
        FixedIterationTrainer fit = new FixedIterationTrainer(ga, 1000);
        
        long startTime = System.currentTimeMillis();
        fit.train();
        long endTime = System.currentTimeMillis();
        System.out.println("\nRunning time: " + (endTime - startTime));
        
        optimalValue = evaluationFunction.value(ga.getOptimal());
		
		return optimalValue;
	}

	
}

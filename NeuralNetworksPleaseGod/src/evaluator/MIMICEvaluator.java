package evaluator;

import opt.EvaluationFunction;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;
import dist.DiscreteDependencyTree;
import dist.Distribution;

public class MIMICEvaluator implements IRandomizedOptimizationEvaluator{

	private static final int numberOfGeneratedSamples = 200;
	private static final int numberOfSamplesToKeep = 100;
	
	@Override
	public double getOptimalValue(EvaluationFunction evaluationFunction,
			Distribution distribution, int[] ranges, int numberOfIterations) {
		
		double optimalValue;
		
		System.out.println("\n-------------------");
    	System.out.println("\nMIMIC");
    	System.out.println("\n-------------------");
    	System.out.println("\nNumber of Iterations: " + numberOfIterations);

		Distribution df = new DiscreteDependencyTree(.1, ranges); 
		ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(evaluationFunction, distribution, df);		

		MIMIC mimic = new MIMIC(numberOfGeneratedSamples, numberOfSamplesToKeep, pop);
        FixedIterationTrainer fit = new FixedIterationTrainer(mimic, numberOfIterations);

    	long startTime = System.currentTimeMillis();
        fit.train();
        long endTime = System.currentTimeMillis();
        System.out.println("\nRunning time: " + (endTime - startTime));

        optimalValue = evaluationFunction.value(mimic.getOptimal());
		
		return optimalValue;
	}

}

package evaluator;

import opt.EvaluationFunction;
import dist.Distribution;

public interface IRandomizedOptimizationEvaluator {

	public double getOptimalValue(EvaluationFunction evaluationFunction, Distribution distribution, int[] ranges, int numberOfIterations);
}

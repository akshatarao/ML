package runner;

import opt.OptimizationAlgorithm;
import opt.RandomizedHillClimbing;
import opt.example.NeuralNetworkOptimizationProblem;

/**
 * 
 * @author akshata
 *
 */
public class RandomHillClimbingRunner extends GenericNNRandomizedOptimizationRunner{

	public RandomHillClimbingRunner(String dataSetFilePath) {
		super(dataSetFilePath);
	}
	
	/**
	 * Optimize Weights For NeuralNetworks Using Randomized Hill Climbing for Optimization Weights
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception
	{
		String datasetFilePath = args[0];
		int numberOfIterations = Integer.valueOf(args[1]);
		int percentageSplit = Integer.valueOf(args[2]);
		
		new RandomHillClimbingRunner(datasetFilePath).run(numberOfIterations, percentageSplit);
	}
	
	@Override
	protected OptimizationAlgorithm getOptimizationAlgorithm(
			NeuralNetworkOptimizationProblem problem) {
		
		return new RandomizedHillClimbing(problem);
	}

}

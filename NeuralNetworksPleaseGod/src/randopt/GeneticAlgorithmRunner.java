package randopt;

import opt.OptimizationAlgorithm;
import opt.example.NeuralNetworkOptimizationProblem;
import opt.ga.StandardGeneticAlgorithm;

/**
 * 
 * @author akshata
 *
 */
public class GeneticAlgorithmRunner extends GenericNNRandomizedOptimizationRunner{

	private int populationSize;
	private int matingCount;
	private int mutatingCount;

	public int getPopulationSize() {
		return populationSize;
	}

	public int getMatingCount() {
		return matingCount;
	}

	public int getMutatingCount() {
		return mutatingCount;
	}

	public GeneticAlgorithmRunner(String dataSetFilePath, int populationSize, int matingCount, int mutatingCount) {
		super(dataSetFilePath);
		
		this.populationSize = populationSize;
		this.matingCount = matingCount;
		this.mutatingCount = mutatingCount;
		
	}

	@Override
	protected OptimizationAlgorithm getOptimizationAlgorithm(NeuralNetworkOptimizationProblem problem) {

        return new StandardGeneticAlgorithm(populationSize, matingCount, mutatingCount, problem);
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		String datasetFilePath = args[0];
		int numberOfIterations = Integer.valueOf(args[1]);
		int percentageSplit = Integer.valueOf(args[2]);
		int populationCount = Integer.valueOf(args[3]);
		int matingCount = Integer.valueOf(args[4]);
		int mutationCount = Integer.valueOf(args[5]);
		
		//TODO: Input Validation
		
		GeneticAlgorithmRunner runner = new GeneticAlgorithmRunner(datasetFilePath, populationCount, matingCount, mutationCount);
		runner.run(numberOfIterations, percentageSplit);
		
	}

}

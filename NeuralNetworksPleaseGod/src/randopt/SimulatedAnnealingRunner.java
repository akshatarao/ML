package randopt;

import opt.OptimizationAlgorithm;
import opt.SimulatedAnnealing;
import opt.example.NeuralNetworkOptimizationProblem;

/**
 * 
 * @author akshata
 *
 */
public class SimulatedAnnealingRunner extends GenericNNRandomizedOptimizationRunner{

	private double startingTemperature;
	private double coolingExponent;
	
	public SimulatedAnnealingRunner(String dataSetFilePath, double startingTemperature, double coolingExponent) {
		super(dataSetFilePath);
		
		this.startingTemperature = startingTemperature;
		this.coolingExponent = coolingExponent;
	}

	@Override
	protected OptimizationAlgorithm getOptimizationAlgorithm(NeuralNetworkOptimizationProblem problem) {
		
		return new SimulatedAnnealing(startingTemperature, coolingExponent, problem);
	}
	
	/**
	 * Run the Simulated Annealing Learner
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String datasetFilePath = args[0];
		int numberOfIterations = Integer.valueOf(args[1]);
		int percentageSplit = Integer.valueOf(args[2]);
		double startingTemperature = Double.valueOf(args[3]);
		double coolingExponent = Double.valueOf(args[4]);
		
		new SimulatedAnnealingRunner(datasetFilePath, startingTemperature, coolingExponent).run(numberOfIterations, percentageSplit);

	}
}

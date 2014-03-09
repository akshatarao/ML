package randopt;

import opt.OptimizationAlgorithm;
import opt.example.NeuralNetworkOptimizationProblem;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;

public class MIMICRunner extends BaseRunner{

	private int numberOfSamplesToGenerate;
	private int numberOfSamplesToKeep;
	
	
	public MIMICRunner(String dataSetFilePath, int numberOfSamplesToGenerate, int numberOfSamplesToKeep) {
		super(dataSetFilePath);
		
		this.numberOfSamplesToGenerate = numberOfSamplesToGenerate;
		this.numberOfSamplesToKeep = numberOfSamplesToKeep;
	}


	@Override
	public void runSpecificRunner(int numberOfIterations, int percentageSplit)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param numberOfSamplesToGenerate
	 * @param numberOfSamplesToKeep
	 * @param problem
	 * @return
	 */
	public OptimizationAlgorithm getOptimizationAlgorithm(int numberOfSamplesToGenerate, int numberOfSamplesToKeep, ProbabilisticOptimizationProblem problem)
	{
		
	}

	

}

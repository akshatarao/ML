package runner;

import dist.Distribution;
import opt.EvaluationFunction;
import opt.OptimizationAlgorithm;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;
import shared.Instance;

public class MIMICRunner extends BaseRunner{

	private int numberOfSamplesToGenerate;
	private int numberOfSamplesToKeep;
	private EvaluationFunction evaluationFunction;
	private Distribution initialDistribution;
	private Distribution distributionFactory;
	
	public MIMICRunner(String dataSetFilePath, int numberOfSamplesToGenerate, int numberOfSamplesToKeep, EvaluationFunction evaluationFunction, Distribution initDistribution, Distribution distributionFactory) {
		super(dataSetFilePath);
		
		this.numberOfSamplesToGenerate = numberOfSamplesToGenerate;
		this.numberOfSamplesToKeep = numberOfSamplesToKeep;
		this.evaluationFunction = evaluationFunction;
		this.initialDistribution = initDistribution;
		this.distributionFactory = distributionFactory;
		
	}


	/**
	 *
	 */
	@Override
	public void runSpecificRunner(int numberOfIterations, int percentageSplit)
			throws Exception {
		
		
		ProbabilisticOptimizationProblem optimizationProblem = new GenericProbabilisticOptimizationProblem(evaluationFunction, initialDistribution, distributionFactory);
		OptimizationAlgorithm optimizationAlgorithm = getOptimizationAlgorithm(optimizationProblem);
        FixedIterationTrainer trainer = new FixedIterationTrainer(optimizationAlgorithm, numberOfIterations);

        long startTime = System.currentTimeMillis();
        trainer.train();
        long endTime  = System.currentTimeMillis();
        
        setTrainingTime(endTime - startTime);
        
        Instance optimizedWeights = optimizationAlgorithm.getOptimal();

		
	}
	
	/**
	 * 
	 * @param numberOfSamplesToGenerate
	 * @param numberOfSamplesToKeep
	 * @param problem
	 * @return
	 */
	public OptimizationAlgorithm getOptimizationAlgorithm(ProbabilisticOptimizationProblem problem)
	{
		return new MIMIC(numberOfSamplesToGenerate, numberOfSamplesToKeep, problem);
	}

	

}

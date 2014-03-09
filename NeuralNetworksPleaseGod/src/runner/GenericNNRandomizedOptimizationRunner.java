package runner;

import opt.OptimizationAlgorithm;
import opt.example.NeuralNetworkOptimizationProblem;

import func.nn.feedfwd.FeedForwardNetwork;
import func.nn.feedfwd.FeedForwardNeuralNetworkFactory;

import shared.DataSetDescription;
import shared.ErrorMeasure;
import shared.FixedIterationTrainer;
import shared.Instance;
import shared.SumOfSquaresError;
import shared.tester.NeuralNetworkTester;
import shared.tester.Tester;

/**
 * 
 * @author Akshata Rao
 *
 */
public abstract class GenericNNRandomizedOptimizationRunner extends BaseRunner{

	

	public GenericNNRandomizedOptimizationRunner(String dataSetFilePath) {
		
		super(dataSetFilePath);
	}
	
		
	/**
	 * Run the Generic Rand Optimization Runner
	 */
	public void runSpecificRunner(int numberOfIterations, int percentageSplit) throws Exception {
		
		
        DataSetDescription desc = dataSet.getDescription();
        DataSetDescription labelDesc = desc.getLabelDescription();

        FeedForwardNeuralNetworkFactory factory = new FeedForwardNeuralNetworkFactory();
		int numberOfInputNodes = desc.getAttributeCount();
		int numberOfOptimalHiddenLayerNodes = factory.getOptimalHiddenLayerNodes(desc, labelDesc);
		int numberOfOutputNodes = labelDesc.getDiscreteRange();
		
		FeedForwardNetwork network = factory.createClassificationNetwork(new int[] {numberOfInputNodes, numberOfOptimalHiddenLayerNodes, numberOfOutputNodes});
        ErrorMeasure measure = new SumOfSquaresError();
        NeuralNetworkOptimizationProblem optimizationProblem = new NeuralNetworkOptimizationProblem( trainingSet, network, measure);

        OptimizationAlgorithm optimizationAlgorithm = getOptimizationAlgorithm(optimizationProblem);

        FixedIterationTrainer trainer = new FixedIterationTrainer(optimizationAlgorithm, numberOfIterations);

        long startTime = System.currentTimeMillis();
        trainer.train();
        long endTime  = System.currentTimeMillis();
        
        setTrainingTime(endTime - startTime);
        
        Instance optimizedWeights = optimizationAlgorithm.getOptimal();
        network.setWeights(optimizedWeights.getData());

        startTime = System.currentTimeMillis();
        
        resetTestMetrics(labelDesc);
        Tester t = new NeuralNetworkTester(network, getRawOutput(), getAccuracyMetric(), getConfusionMatrix());
        t.test(testingSet.getInstances());
        
        endTime  = System.currentTimeMillis();
        
        setTestingTime(endTime - startTime);
        
	}
		
	protected abstract OptimizationAlgorithm getOptimizationAlgorithm(NeuralNetworkOptimizationProblem problem);
	
}

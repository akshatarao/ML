package randopt;

import java.io.File;

import shared.DataSet;
import shared.DataSetDescription;
import shared.filt.LabelSplitFilter;
import shared.filt.TestTrainSplitFilter;
import shared.reader.ArffDataSetReader;
import shared.reader.DataSetLabelBinarySeperator;
import shared.runner.Runner;
import shared.tester.AccuracyTestMetric;
import shared.tester.ConfusionMatrixTestMetric;
import shared.tester.RawOutputTestMetric;

public abstract class BaseRunner implements Runner {
	protected String datasetFilePath;
	protected DataSet dataSet;
	protected DataSet trainingSet;
	protected DataSet testingSet;
	
	protected AccuracyTestMetric accuracy;
	protected ConfusionMatrixTestMetric confusionMatrix;
	protected RawOutputTestMetric rawOutput;
	
	protected long trainingTime;
	protected long testingTime;
	
	BaseRunner(String datasetFilePath)
	{
		this.datasetFilePath = datasetFilePath;
	}
	
	public DataSet getDataSet() {
		return dataSet;
	}

	protected void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public DataSet getTrainingSet() {
		return trainingSet;
	}

	protected void setTrainingSet(DataSet trainingSet) {
		this.trainingSet = trainingSet;
	}

	public DataSet getTestingSet() {
		return testingSet;
	}

	protected void setTestingSet(DataSet testingSet) {
		this.testingSet = testingSet;
	}
	@Override
	public AccuracyTestMetric getAccuracyMetric() {
		return accuracy;
	}

	@Override
	public ConfusionMatrixTestMetric getConfusionMatrix() {
		return confusionMatrix;
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public RawOutputTestMetric getRawOutput() {
		
		return rawOutput;
	}

	@Override
	public long getTestTime() {
		
		return testingTime;
	}

	@Override
	public long getTrainingTime() {
		
		return trainingTime;
	}

	
	
	public long getTestingTime() {
		return testingTime;
	}

	protected void setTestingTime(long testingTime) {
		this.testingTime = testingTime;
	}

	protected void setTrainingTime(long trainingTime) {
		this.trainingTime = trainingTime;
	}

	public AccuracyTestMetric getAccuracy() {
		return accuracy;
	}

	protected void setAccuracy(AccuracyTestMetric accuracy) {
		this.accuracy = accuracy;
	}

	protected void setConfusionMatrix(ConfusionMatrixTestMetric confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	protected void setRawOutput(RawOutputTestMetric rawOutput) {
		this.rawOutput = rawOutput;
	}
	
	/**
	 * Reset Test Metric
	 * @param labelDescription
	 */
	protected void resetTestMetrics(DataSetDescription labelDescription)
	{
		setAccuracy(new AccuracyTestMetric());
		setConfusionMatrix(new ConfusionMatrixTestMetric(labelDescription));
		setRawOutput(new RawOutputTestMetric());
	}
	
	
	/**
	 * Split Data Set
	 * @param percentageSplit
	 * @param set
	 */
	protected void splitDataSet(int percentageSplit, DataSet set)
	{
		DataSet trainingSet;
		DataSet testingSet;
		
		if (percentageSplit > 0 && percentageSplit < 100) 
		{
            TestTrainSplitFilter ttsf = new TestTrainSplitFilter(percentageSplit);
            ttsf.filter(set);
            
            trainingSet = ttsf.getTrainingSet();
            testingSet = ttsf.getTestingSet();
            
        } 
		else 
		{
			trainingSet = set;
        	testingSet = set;	
        }
		
		setTrainingSet(trainingSet);
		setTestingSet(testingSet);
	}

	/**
	 * Read ARFF Dataset
	 * @param dataFilePath
	 * @return
	 */
	protected DataSet readARFFDataSet(String dataFilePath)
	{
		DataSet set = null;
		try
		{
			ArffDataSetReader reader = new ArffDataSetReader(dataFilePath);
	        set = reader.read();
			LabelSplitFilter flt = new LabelSplitFilter();
	        flt.filter(set);
	        DataSetLabelBinarySeperator.seperateLabels(set);
		}
		catch(Exception e)
		{
			System.out.println("\nError occured " + e.getMessage());
		}
		
		return set;
	}

	/**
	 * @Override
	 * @param numberOfIterations
	 */
	public void run(int numberOfIterations, int percentageSplit) throws Exception
	{
		//InputValidation
		if(!new File(this.datasetFilePath).exists())
		{
			throw new Exception("Data File does not exist" + datasetFilePath);
		}
		
		DataSet set;
		if((set = readARFFDataSet(this.datasetFilePath)) == null)
		{
			throw new Exception("Dataset was NULL");
		}
		
	
		setDataSet(set);
		splitDataSet(percentageSplit, dataSet);
		runSpecificRunner(numberOfIterations, percentageSplit);

	}
	
	public abstract void runSpecificRunner(int numberOfIterations, int percentageSplit) throws Exception;

}

public class BaseClass{
	public static final double EPS=1e-6;
	static class Job{
		public double at;
		public double bt;
		public String name;
		public Job(double at,double bt,String name){
			this.at=at;
			this.bt=bt;
			this.name=name;
		}
	}
	static class TimeSlice{
		public String jobName;
		public double startTime;
		public TimeSlice(String jobName,double startTime){
			this.jobName=jobName;
			this.startTime=startTime;
		}
		public void show(){
			System.out.print(String.format("|Start time: %.2f Job: %s",startTime,jobName));
		}
	}
}



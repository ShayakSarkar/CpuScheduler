import java.util.LinkedList;

public class BaseClass{
	public static final double EPS=1e-6;
	static class Job{
		public double at;
		public double bt;
		public String name;
		public int priority;
		public Job(double at,double bt,String name){
			this.at=at;
			this.bt=bt;
			this.name=name;
		}
		public Job(double at,double bt,String name,int priority){
			this.at=at;
			this.bt=bt;
			this.name=name;
			this.priority=priority;
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
	static LinkedList<TimeSlice> refineGanttChart(LinkedList<TimeSlice> ganttChart){
		for(int i=0;i<ganttChart.size()-1;){
			String processName=ganttChart.get(i).jobName;
			int j=i+1;
			while(j<=ganttChart.size() && ganttChart.get(j).jobName==processName){
				ganttChart.remove(j);
				j++;
			}
			i=j;
		}
		return ganttChart;
	}
}



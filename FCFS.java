import java.util.Queue;
import java.util.Scanner;

public class FCFS extends BaseClass{
	public static LinkedList<TimeSlice> ganttChart performFCFS(Job[] jobs){
		if(jobs.length == 0)
			throw new IllegalArgumentExeption("No jobs available");

		Queue<Job> q=new Queue<Job>();
		if(q==null)
			throw new IllegalArgumentExeption("Queue could not be made");

		LinkedList<TimeSlice> ganttChart=new LinkedList<TimeSlice>();
		if(ganntChart==null)
			throw new IllegalArgumentException();
		
		double timeElapsed=0;
		int jobno=-1;		//index of the last job enqueued

		while(jobno<jobs.length-1 || !q.isEmpty()){
			if(q.isEmpty()){
				timeElapsed=jobs[jobno+1].at;
				jobno++;
				while(timeElapsed==jobs[jobno].at)
					q.enqueue(jobs[jobno]);
				continue;
			}
			
			Job selected=q.dequeue();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);
			timeElapsed+=selected.bt;
			
			while(jobno<jobs.length-1 && timeElapsed>=jobs[jobno+1]){
				q.enqueue(jobs[jobno+1]);
				jobno++;
			}
		}
		return refineGanttChart(ganttChart);
	}
	public static LinkedList<TimeSlice> refineGanttChart(LinkedList<TimeSlice> ganttChart){
		for(int i=0;i<ganttChart.size()-1;){
			String processName=ganttChart.get(i).jobName;
			int j=i+1;
			while(j<ganttChart.size()){
				ganttChart.remove(j);
				j++:
			}
			i=j;
		}
		return ganttChart;
	}
	public static void main(String args[]){
		Job[] jobs=new Job[5];
		jobs[0]=new Job();
		jobs[1]=new Job();
		jobs[2]=new Job();
		jobs[3]=new Job();
		LinkedList<TimeSlice> ganttChart=performFCFS(jobs);
		System.out.println("Hello World");
	}
}	










		



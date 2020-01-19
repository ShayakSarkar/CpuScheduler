import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class FCFS extends BaseClass{
	public static LinkedList<TimeSlice> performFCFS(Job[] jobs){
		if(jobs.length == 0)
			throw new IllegalArgumentException("No jobs available");

		Queue<Job> q=new LinkedList<Job>();
		if(q==null)
			throw new IllegalArgumentException("Queue could not be made");

		LinkedList<TimeSlice> ganttChart=new LinkedList<TimeSlice>();
		if(ganttChart==null)
			throw new IllegalArgumentException();
		
		double timeElapsed=0;
		int jobno=-1;		//index of the last job enqueued

		while(jobno<jobs.length-1 || !q.isEmpty()){
			//System.out.println(q);	
			if(q.isEmpty()){
				timeElapsed=jobs[jobno+1].at;
				q.add(jobs[jobno+1]);
				jobno++;
				while(jobno<jobs.length-1 && timeElapsed==jobs[jobno+1].at){
					q.add(jobs[jobno+1]);
					jobno++;
				}

				continue;
			}
			Job selected=q.remove();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			//ts.show();
			ganttChart.add(ts);
			timeElapsed+=selected.bt;
			
			while(jobno<jobs.length-1 && timeElapsed>=jobs[jobno+1].at){
				q.add(jobs[jobno+1]);
				jobno++;
			}
		}
		return refineGanttChart(ganttChart);
	}
	public static void main(String args[]){
		Job[] jobs=new Job[3];
		jobs[0]=new Job(0,21,"P1");
		jobs[1]=new Job(0,3,"P2");
		jobs[2]=new Job(0,4,"P3");
		//jobs[3]=new Job(0,);
		LinkedList<TimeSlice> ganttChart=performFCFS(jobs);
		System.out.println("Gantt Chart");

		for(TimeSlice timeSlice : ganttChart)
			timeSlice.show();

	}
}	










		



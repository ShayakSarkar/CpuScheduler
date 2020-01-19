import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.Math;

public class SJF{
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
			System.out.print(String.format("|StartTime: %.2f Job: %s |",startTime,jobName));
		}
	
	}

	public static LinkedList<TimeSlice> performSJF(Job[] jobs){	
		//GantChart array will have the time slices for all the jobs
		if(jobs.length==0)
			throw new IllegalArgumentException("Job array has no values");
		
		Comparator<Job> comparator=new Comparator<Job>(){	// For comparing the values in Priority Queue
			public  int compare(Job job1,Job job2){
				if(job1.bt-job2.bt<=EPS)
					return 0;
				return (job1.bt-job2.bt)<0?-1:1;
			}
		};
		PriorityQueue<Job> pq=new PriorityQueue<Job>(comparator);	// Maintains the optimal jobs available
		
		if(pq==null)
			throw new IllegalArgumentException("Priority Queue could not be initialised");

		LinkedList<TimeSlice> ganttChart=new LinkedList<TimeSlice>();
		
		if(ganttChart==null)
			throw new IllegalArgumentException("Gantt Chart could not be initialised");

		System.out.println("Echo1");
		Arrays.sort(jobs,comparator);
		System.out.println("Echo2");
		int jobno=0;		//Maintains the job number last pushed into the pq
		pq.offer(jobs[jobno]);
		double timeElapsed=jobs[jobno].at;
		
		//Execution until all jobs are in the priority queue
		while(jobno<jobs.length-1){
			System.out.println(timeElapsed);
			if(pq.isEmpty()){
				timeElapsed=jobs[jobno+1].at;
				pq.offer(jobs[jobno+1]);
				jobno++;
				continue;
			}
			Job selected=pq.poll();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);
			double duration=Math.min(selected.bt,jobs[jobno].at-timeElapsed);
			selected.bt-=duration;
			if(!(selected.bt<=0))
				pq.offer(selected);
			timeElapsed+=duration;
			jobno++;
			pq.offer(jobs[jobno]);
		}

		//Execution after all jobs are in the priority queue
		while(!pq.isEmpty()){
			Job selected=pq.poll();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);
			timeElapsed+=selected.bt;
		}
		return ganttChart;
	
	}
	public static void main(String args[]){
		Job[] jobs=new Job[5];
		jobs[0]=new Job(0,7,"P1");
		jobs[1]=new Job(2,4,"P2");
		jobs[2]=new Job(3,1,"P3");
		jobs[3]=new Job(5,4,"P4");
		//jobs[4]=new Job(5,8,"P5");
		
		System.out.println("Jobs Inititalised...");

		System.out.println("Generating gantt chart");
		LinkedList<TimeSlice> ganttChart=performSJF(jobs);

		System.out.println("Gantt Chart: \n");
		for(TimeSlice slice : ganttChart)
			slice.show();
		System.out.println();
	}
}

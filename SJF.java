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
		Scanner in=new Scanner(System.in);
		//GantChart array will have the time slices for all the jobs
		if(jobs.length==0)
			throw new IllegalArgumentException("Job array has no values");
		
		Comparator<Job> pqComparator=new Comparator<Job>(){	// For comparing the values in Priority Queue
			public  int compare(Job job1,Job job2){
				if(Math.abs(job1.bt-job2.bt)<=EPS)
					return 0;
				return (job1.bt-job2.bt)>0?1:-1;
			}
		};
		Comparator<Job> sortComparator=new Comparator<Job>(){
			public int compare(Job job1,Job job2){
				if(Math.abs(job1.at-job2.at)<=EPS)
					return 0;
				return (job1.at-job2.at>0)?1:-1;
			}

		};
		PriorityQueue<Job> pq=new PriorityQueue<Job>(pqComparator);	// Maintains the optimal jobs available
		
		if(pq==null)
			throw new IllegalArgumentException("Priority Queue could not be initialised");

		LinkedList<TimeSlice> ganttChart=new LinkedList<TimeSlice>();
		
		if(ganttChart==null)
			throw new IllegalArgumentException("Gantt Chart could not be initialised");

		//System.out.println("Echo1");
		Arrays.sort(jobs,sortComparator);
		/*
		for(Job job : jobs){
			System.out.print(job.name+" ");
		}
		*/
		//System.out.println("Echo2");
		int jobno=-1;		//Maintains the job number last arrived in
		double timeElapsed=0;
		
		//Execution until all jobs are in the priority queue
		while(jobno<jobs.length-1){
			//System.out.println(timeElapsed);
			if(pq.isEmpty()){
				timeElapsed=jobs[jobno+1].at;
				while(jobno<jobs.length-1 && jobs[jobno+1].at==timeElapsed){
					pq.offer(jobs[jobno+1]);
					jobno++;
				}
				continue;
			}
			Job selected=pq.poll();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);
			double duration=Math.min(selected.bt,jobs[jobno+1].at-timeElapsed);
			selected.bt-=duration;
			if(!(selected.bt<=0))
				pq.offer(selected);
			timeElapsed+=duration;
			while(jobno<jobs.length-1 && timeElapsed==jobs[jobno+1].at){
				jobno++;
				pq.offer(jobs[jobno]);
			}
		}

		//Execution after all jobs are in the priority queue
		while(!pq.isEmpty()){
			Job selected=pq.poll();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);
			timeElapsed+=selected.bt;
		}
		return refineGanttChart(ganttChart);
	
	}
	public static LinkedList<TimeSlice> refineGanttChart(LinkedList<TimeSlice> ganttChart){
		int i=0;
		LinkedList<TimeSlice> refinedGanttChart=new LinkedList<TimeSlice>();
		while(i<ganttChart.size()){
			refinedGanttChart.add(ganttChart.get(i));
			String processName=ganttChart.get(i).jobName;
			int j=i+1;
			while(j<ganttChart.size() && processName==ganttChart.get(j).jobName){
				j++;
			}
			i=j;
		}
		return refinedGanttChart;

	}
	public static void main(String args[]){
		Job[] jobs=new Job[5];
		jobs[0]=new Job(0,12,"P2");
		jobs[1]=new Job(3,8,"P3");
		jobs[2]=new Job(5,4,"P4");
		jobs[3]=new Job(10,10,"P1");
		jobs[4]=new Job(12,6,"P5");
		
		System.out.println("Jobs Inititalised...");

		System.out.println("Generating gantt chart");
		LinkedList<TimeSlice> ganttChart=performSJF(jobs);

		System.out.println("Gantt Chart: \n");
		for(TimeSlice slice : ganttChart)
			slice.show();
		System.out.println();
	}
}

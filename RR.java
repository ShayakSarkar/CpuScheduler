import java.util.Queue;
import java.util.LinkedList;

public class RR extends BaseClass{
	public static LinkedList<TimeSlice> performRR(Job[] jobs,double quantum){	//jobs is the job pool
		if(quantum<=0)
			throw new IllegalArgumentException("Bad time quantum");

		if(jobs==null || jobs.length==0)
			throw new IllegalArgumentException("Job array Invalid");

		Queue<Job> q=new LinkedList<Job>();
		if(q==null)
			throw new IllegalArgumentException("Queue could not be formed");

		LinkedList<TimeSlice> ganttChart=new LinkedList<TimeSlice>();
		if(ganttChart==null)
			throw new IllegalArgumentException("Gantt Chart could not be formed");
		
		int jobno=-1;	// Keeps track of the last job pushed into the queue from the job pool
		double timeElapsed=0;
		while(jobno<jobs.length-1 || !q.isEmpty()){
			if(q.isEmpty()){
				timeElapsed=jobs[jobno+1].at;
				q.add(jobs[jobno+1]);
				jobno++;
				while(jobno<jobs.length-1 && timeElapsed==jobs[jobno+1].at){
					q.add(jobs[jobno+1]);
					jobno++;
				}

			}
			Job selected=q.remove();
			TimeSlice ts=new TimeSlice(selected.name,timeElapsed);
			ganttChart.add(ts);

			double duration=Math.min(selected.bt,quantum);
			selected.bt-=duration;
			timeElapsed+=duration;
			while(jobno<=jobs.length-1 && timeElapsed>=jobs[jobno+1].at){
				q.add(jobs[jobno+1]);
				jobno++;
			}
			if(selected.bt>0)
				q.add(selected);
		}
		return refineGanttChart(ganttChart);
	}
	public static void main(String args[]){
		/*Job[] jobs=new Job[5];
		jobs[0]=new Job();
		jobs[1]=new Job();
		jobs[2]=new Job();
		jobs[3]=new Job();
		double quantum=5;*/
		//LinkedList<TimeSlice> ganttChart=performRR(jobs,quantun);
	}
}	
			






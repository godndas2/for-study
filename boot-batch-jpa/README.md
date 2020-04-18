# Bath == ETL
> Extract, Transformation, Load

## JobRepository
Schema에 Metadata와 Batch 실행 계획을 저장한다.  
Job과 Step등의 정보를 순차적으로 실행한다.  

## Job
> 하나의 배치 실행 단위
  
- JobInstance : Job을 식별하기 위함  
- JobParameter : Job 실행에 필요한 매개변수  
- JobExecution : JobInstance가 실행되는 것(JobInstance와 1대1 관계)

## Step
Job에서 실행하는 실행단위.  
 > ItemReader > ItemProcessor > ItemWriter
 
- ItemReader는 데이터를 읽어들이거나, File과 같은 것을 읽어들일 때 사용
- ItemProcessor는 읽어들인 데이터를 가공할 내용을 정의합니다. 생략할 수도 있다.
- ItemWriter는 읽어들인 데이터 또는 가공이 끝난 데이터를 저장할 때 사용  

## Tasklet & Chunk
Chunk : 대용량 데이터처럼 한 번에 로드하기 힘든 작업을 위해  
일정한 크기로 나누어 읽고, 가공하고, 저장한다.  

[참고] https://jojoldu.tistory.com/331  
  

 

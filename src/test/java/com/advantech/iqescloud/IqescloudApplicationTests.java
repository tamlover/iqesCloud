package com.advantech.iqescloud;

import com.advantech.iqescloud.entity.DTO.queueInfo.AverageQueueTimeDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.ChurnRateDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.QueueInfoOfRestaurantDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.TableTypePercentageDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsChurnRateContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueueTimeContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueuesContrastDTO;
import com.advantech.iqescloud.entity.POJO.TableTypeQueueTimePOJO;
import com.advantech.iqescloud.entity.QueueInfo;
import com.advantech.iqescloud.entity.Restaurant;
import com.advantech.iqescloud.repository.QueueInfoDao;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.advantech.iqescloud.service.QueueInfoService;
import com.advantech.iqescloud.service.RestaurantService;
import com.advantech.iqescloud.utils.TimeFormatTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IqescloudApplicationTests {


	@Autowired
	private QueueInfoService queueInfoService;

	@Autowired
	private QueueInfoDao queueInfoDao;

	@Autowired
	private RestaurantDao restaurantDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() throws ParseException {
		List<QueueInfoOfRestaurantDTO> queueInfos=queueInfoService.getQueueInfosByRestaurantAndDate((long)1,"2017-12-13");
		System.out.println("_____________________________");
		System.out.println(queueInfos.size());
		System.out.println("_____________________________");
	}

	@Test
	public void addQueueInfo() throws ParseException {
		Random random=new Random();
		Restaurant restaurant=restaurantDao.findOne((long)3);
		for (char name='a';name<='z';name++) {
			QueueInfo queueInfo=new QueueInfo();

			int startTime=random.nextInt(30);
			int queueTime=random.nextInt(20)+5;
			int miss=random.nextInt(5);
			int table=random.nextInt(6);

			queueInfo.setRestaurant(restaurant);
			queueInfo.setCustomerName(String.valueOf(name));
			queueInfo.setCustomerTel(random.nextInt(23)+100+"1234");
			queueInfo.setEatNumber(random.nextInt(8)+3);

			queueInfo.setQueueStartTime(TimeFormatTool.getMinutesAfterFromNow(startTime));
			queueInfo.setQueueEndTime(TimeFormatTool.getMinutesAfter(queueInfo.getQueueStartTime(),queueTime));

			if (miss==3){
			    queueInfo.setQueueState("3");
			}else {
				queueInfo.setQueueState("2");
			}


			if (table<=3) {
				queueInfo.setTableTypeDescribe("小桌");
			}else if (table<=5){
				queueInfo.setTableTypeDescribe("中桌");
			}else {
				queueInfo.setTableTypeDescribe("大桌");
			}

			queueInfo.setQueueTime(queueTime);
			queueInfo.setSeatFlag(false);
			queueInfo.setTableNumber("A"+(random.nextInt(100)+100));

			queueInfoDao.save(queueInfo);
		}
	}

	@Test
	public void testDao(){
//	List<TableTypeQueueTimePOJO> tableTypeQueueTimePOJOS=queueInfoService.getAverageQueueTimeByDay((long)1,"2017-12-12%");
//	for(TableTypeQueueTimePOJO t:tableTypeQueueTimePOJOS){
//		System.out.println(t);
//	}
	}

	@Test
	public void test22() throws ParseException {
		List<String> week=TimeFormatTool.getNextWeek("2017-12-12");
		for (String day:week){
			System.out.println(day);
		}
	}

	@Test
	public void addQueueInfoNextDay() throws ParseException {
		Random random=new Random();
		Restaurant restaurant=restaurantDao.findOne((long)3);
		for (char name='a';name<='z';name++) {
			QueueInfo queueInfo=new QueueInfo();

			int startTime=random.nextInt(30);
			int queueTime=random.nextInt(20)+5;
			int miss=random.nextInt(5);
			int table=random.nextInt(7);

			queueInfo.setRestaurant(restaurant);
			queueInfo.setCustomerName(String.valueOf(name)+String.valueOf(name));
			queueInfo.setCustomerTel(random.nextInt(23)+100+"1234");
			queueInfo.setEatNumber(random.nextInt(8)+3);

			queueInfo.setQueueStartTime(TimeFormatTool.getDaysAfterFromNow(1 ,startTime));
			queueInfo.setQueueEndTime(TimeFormatTool.getMinutesAfter(queueInfo.getQueueStartTime(),queueTime));

			if (miss==3){
				queueInfo.setQueueState("2");
			}else {
				queueInfo.setQueueState("3");
			}


			if (table<=3) {
				queueInfo.setTableTypeDescribe("小桌");
			}else if (table<=5){
				queueInfo.setTableTypeDescribe("中桌");
			}else {
				queueInfo.setTableTypeDescribe("大桌");
			}

			queueInfo.setQueueTime(queueTime);
			queueInfo.setSeatFlag(false);
			queueInfo.setTableNumber("A"+(random.nextInt(100)+100));

			queueInfoDao.save(queueInfo);
		}
	}

	@Test
	public void testService() throws ParseException {
		List<AverageQueueTimeDTO> averageQueueTimeDTOList=queueInfoService.getAverageQueueTime((long)1,"2017-12-12");
		for (AverageQueueTimeDTO a:averageQueueTimeDTOList){
			System.out.println(a);
		}
	}

	@Test
	public void testTableTypePercentage() throws ParseException {
		List<TableTypePercentageDTO> tableTypePercentageDTOS= queueInfoService.getTableTypePercentage((long)1);
		for (TableTypePercentageDTO t:tableTypePercentageDTOS){
			System.out.println(t);
		}
	}

	@Test
	public void testChurn(){
		int r=queueInfoDao.getByQueueStartTime("2017-12-12%",(long)1);
		int g=queueInfoDao.getByQueueStartTimeAndQueueState("2017-12-12%",(long)1);
		System.out.println("r:"+r);
		System.out.println("g:"+g);

		DecimalFormat decimalFormat=new DecimalFormat("0.0000");
		System.out.println(decimalFormat.format((float)g/r));
	}

	@Test
	public void testGetChurnRateDTO() throws ParseException {
//		List<ChurnRateDTO> churnRateDTOList=queueInfoService.getChurnRateDTO((long)1,"2017-12-12");
//		for (ChurnRateDTO c:churnRateDTOList){
//			System.out.println(c);
//		}
	}

	@Test
	public void  updateTest(){
		queueInfoDao.update2();
	}

	@Test
	public void testManyRestaurantsAverageTime(){
		Long[] r=new Long[]{(long)1,(long)2};
		List<RestaurantsQueueTimeContrastDTO> queueTimeContrastDTOS=queueInfoService.getRestaurantsQueueTimeContrast(r,"2017-12-13");
		System.out.println(queueTimeContrastDTOS.size());
		System.out.println("______________________________");
		for (RestaurantsQueueTimeContrastDTO restaurantsQueueTimeContrastDTO:queueTimeContrastDTOS){
			System.out.println(restaurantsQueueTimeContrastDTO);
		}
	}

	@Test
	public void testChurnRate(){
		Long[] r=new Long[]{(long)1,(long)2};
		List<RestaurantsChurnRateContrastDTO> rr=queueInfoService.getRestaurantsChurnRateContrast(r,"2017-12-13");
		for (RestaurantsChurnRateContrastDTO q:rr){
			System.out.println(q);
		}
	}

	@Test
	public void testQueues(){
		Long[] r=new Long[]{(long)1,(long)2};
		List<RestaurantsQueuesContrastDTO> rr=queueInfoService.getRestaurantsQueuesContrash(r,"2017-12-13");
		for (RestaurantsQueuesContrastDTO q:rr){
			System.out.println(q);
		}
	}

	@Test
	public void addQueueInfoQueueTimeLessThan5() throws ParseException {
		Random random=new Random();
		List<QueueInfo> queueInfos=queueInfoDao.getByTableTypeDescribe("小桌");
		for (QueueInfo q:queueInfos){
			int queueTime=random.nextInt(9)+3;
			System.out.println("queueTime:"+queueTime);
			q.setQueueTime(queueTime);
			queueInfoDao.save(q);

		}
	}

	@Test
	public void addQueueInfoQueueTimeMoreThan30() throws ParseException {
		Random random=new Random();
		List<QueueInfo> queueInfos=queueInfoDao.getByTableTypeDescribe("中桌");
		for (QueueInfo q:queueInfos){
			int queueTime=random.nextInt(20)+15;
			System.out.println("queueTime:"+queueTime);
			q.setQueueTime(queueTime);
			queueInfoDao.save(q);

		}
	}

	@Test
	public void testTableType(){
		List<String> tables=queueInfoDao.getTableTypeDescribe((long)3);
		for (String t:tables){
			System.out.println(t);
		}
	}

	@Test
	public void testQueueTime() throws ParseException {
//		List<QueueInfo> queueInfos=queueInfoDao.getByQueueTimeBetween((long)1,"小桌",3,7);
//		System.out.println(queueInfos.size());
//		List<QueueInfo> queueInfoList=queueInfoDao.getByQueueTimeBetweenAndQueueState((long)1,"小桌",3,7);
//		System.out.println(queueInfoList.size());

		List<ChurnRateDTO> churnRateDTOList=queueInfoService.getChurnRateDTO((long)1);
		for (ChurnRateDTO c:churnRateDTOList){
			System.out.println("________________________________");
			System.out.println(c);
			System.out.println("________________________________");

		}
	}

	public void addQueueInfoBefore(long restaurantId,int beforDay,char n)throws ParseException {
		Random random=new Random();
		Restaurant restaurant=restaurantDao.findOne(restaurantId);
		for (char name='a';name<=n;name++) {
			QueueInfo queueInfo=new QueueInfo();

			int startTime=random.nextInt(30);
			int queueTime=random.nextInt(20)+5;
			int miss=random.nextInt(5);
			int table=random.nextInt(7);

			queueInfo.setRestaurant(restaurant);
			queueInfo.setCustomerName(String.valueOf(name)+String.valueOf(name));
			queueInfo.setCustomerTel(random.nextInt(23)+100+"1234");
			queueInfo.setEatNumber(random.nextInt(8)+3);

			queueInfo.setQueueStartTime(TimeFormatTool.getDaysBeforFromNow(beforDay ,startTime));
			queueInfo.setQueueEndTime(TimeFormatTool.getMinutesAfter(queueInfo.getQueueStartTime(),queueTime));

			if (miss==3){
				queueInfo.setQueueState("2");
			}else {
				queueInfo.setQueueState("3");
			}


			if (table<=3) {
				queueInfo.setTableTypeDescribe("小桌");
			}else if (table<=5){
				queueInfo.setTableTypeDescribe("中桌");
			}else {
				queueInfo.setTableTypeDescribe("大桌");
			}

			queueInfo.setQueueTime(queueTime);
			queueInfo.setSeatFlag(false);
			queueInfo.setTableNumber("A"+(random.nextInt(100)+100));

			queueInfoDao.save(queueInfo);
		}
	}

	@Test
	public void testAdd() throws ParseException {
		Random random=new Random();
		for (int i=4;i<=5;i++){
			int n=random.nextInt(20);
			System.out.println(n);
			char name='f';

			switch (n/2){
				case 1:name='d';break;
				case 2:name='r';break;
				case 3:name='t';break;
				case 4:name='q';break;
				case 5:name='w';break;
				case 6:name='o';break;
				case 7:name='n';break;
				case 9:name='s';break;
				case 8:name='x';break;
			}

			for (int day=3;day<5;day++){
				System.out.println("i:"+i);
				System.out.println("day"+day);
				System.out.println("name:"+name);
				addQueueInfoBefore(i,day,name);
			}
		}
	}
}

package com.platform.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.DataQueryDao;
import com.platform.business.dto.DataQueryDto;
import com.platform.business.service.DataQueryService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class DataQueryServiceImpl extends BaseServiceImpl<DataQueryDto> implements DataQueryService {

	@Autowired
	private DataQueryDao dao;
	
	@Override
	protected BaseDao<DataQueryDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid queryForecast(DataQueryDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryForecast(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryForecastCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid queryLive(DataQueryDto dto) {
		
		DataGrid dg = new DataGrid();
		if(dto.getStation_id() == null || "".equals(dto.getStation_id()) || "".equals(dto.getData_id()))
			return dg;
		
		
		List<Map<String, Object>> list = dao.queryLiveColumns(dto.getData_id());
		
		if(list == null || list.isEmpty())
			return dg;
		
		StringBuffer sb = new StringBuffer();
		
		if(dto.getPlay_time() == null || "".equals(dto.getPlay_time())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dto.setPlay_time(sdf.format(new Date()));
		}
		
//		sb.append("select ida= '-2',name='站号', data='" + dto.getStation_id() + "' ");
//		sb.append("union all ");
		sb.append("select ida= '-1',name='站名', data=stationname from awstation where awstation_id = '" + dto.getStation_id() + "' ");

				
		if ("9".equals(dto.getData_id())) {
			if("".equals(dto.getPlay_time1()))
				return null;
			sb.append("union all ");
			sb.append("select ida= '0',name='时间', data='" + dto.getPlay_time1().substring(0, 10) + " ~ " + dto.getPlay_time().substring(0, 10) + "'");
			sb.append(" union all ");
			
			sb.append("SELECT ida= 1, name='积温', CAST(SUM(day_average_temp) as VARCHAR(max)) data from (" +
					"select station_id,time, CAST(day_average_temp as FLOAT) day_average_temp from awstation_data_calculate where " +
					"time BETWEEN '"+dto.getPlay_time1().substring(0, 10)+"' and '"+dto.getPlay_time().substring(0, 10)+"' and station_id = (select stationnum from awstation where awstation_id = '"+dto.getStation_id()+"')" +
					") x GROUP BY station_id");
			sb.append(" union all ");
			sb.append("SELECT ida= 2, name='正积温', CAST(SUM(day_average_temp) as VARCHAR(max)) data  from (" +
					"select station_id,time, CASE  WHEN CAST(day_average_temp as FLOAT) < 0 THEN 0 ELSE CAST(day_average_temp as FLOAT) end day_average_temp from awstation_data_calculate where " +
					"time BETWEEN '"+dto.getPlay_time1().substring(0, 10)+"' and '"+dto.getPlay_time().substring(0, 10)+"' and station_id = (select stationnum from awstation where awstation_id = '"+dto.getStation_id()+"')" +
					") x GROUP BY station_id");
			sb.append(" union all ");
			sb.append("SELECT ida= 3, name='负积温', CAST(SUM(day_average_temp) as VARCHAR(max)) data from (select station_id,time, CASE  WHEN CAST(day_average_temp as FLOAT) > 0 THEN 0 ELSE CAST(day_average_temp as FLOAT) end day_average_temp from awstation_data_calculate where " +
					"time BETWEEN '"+dto.getPlay_time1().substring(0, 10)+"' and '"+dto.getPlay_time().substring(0, 10)+"' and station_id = (select stationnum from awstation where awstation_id = '"+dto.getStation_id()+"')" +
					") x GROUP BY station_id ORDER BY ida");
			
		} else {
			sb.append("union all ");
			sb.append("select ida= '-0',name='时间', data=CONVERT(varchar(20),time,120) from (select MAX(_time) time from " + list.get(0).get("data_table") + " where _stationnum = (select stationnum from awstation where awstation_id = '" + dto.getStation_id() + "') AND _time between '"+dto.getPlay_time().substring(0, 10) +"' and '"+dto.getPlay_time()+"' GROUP BY _StationNum ) p ");

			String s = "";
			for (int i = 0; i < list.size(); i++) {
				s += "[" + list.get(i).get("data_column") + "],";
			}
			sb.append("union all (");
			sb.append("SELECT ot.observe_id ida, ot.observe_type name, CAST(data as VARCHAR(max)) data from (SELECT id=attribute, data=value FROM "
					+ list.get(0).get("data_table")
					+ "  UNPIVOT ( value FOR attribute IN(");
			sb.append(s.substring(0, s.length() - 1));
			sb.append(")) AS UPV ");

			sb.append("where _stationnum = (select stationnum from awstation where awstation_id = '" + dto.getStation_id()
					+ "') and _time = (select MAX(_time) from "
					+ list.get(0).get("data_table") + " where _stationnum = (select stationnum from awstation where awstation_id = '"
					+ dto.getStation_id() + "') AND _time between '"
					+ dto.getPlay_time().substring(0, 10) + "' and '"
					+ dto.getPlay_time() + "' GROUP BY _StationNum ) ");
			sb.append(")  t RIGHT JOIN z_observe_type ot ON t.id = ot.data_column where ot.data_id = '"
					+ dto.getData_id() + "' ) ORDER BY ida");

		}
		//		for (int i = 0; i < list.size(); i++) {
//			s += "["+list.get(i).get("data_column")+"],";
//			sb.append("union all ");
//			sb.append("select DISTINCT name='" + list.get(i).get("observe_type") + "', data=CAST( " + list.get(i).get("data_column") + " as VARCHAR(max)) from " + list.get(i).get("data_table") + " ");
//			sb.append("where _stationnum = '" + dto.getStation_id() + "' and _time = (select MAX(_time) from " + list.get(i).get("data_table") + " where _stationnum = '" + dto.getStation_id() + "' AND _time between '"+dto.getPlay_time().substring(0, 10) +"' and '"+dto.getPlay_time()+"' GROUP BY _StationNum )");
//		}
		
//		System.out.println("------------------------------------------------------------------------");
//		System.out.println(sb.toString());
//		System.out.println("------------------------------------------------------------------------");
		dto.setSql(sb.toString());
		List resultList = dao.queryLive(dto);
		
		dg.setRows(resultList);
		
		return dg;
	}

	@Override
	public JSONArray queryStationCombo(String id) {

		List list = dao.queryStationByRegion(id);
		JSONArray json = new JSONArray(list);
		
		return json;
	}
	
	@Override
	public JSONArray queryTypeCombo(String id) {
		
		List list = dao.queryTypeCombo(id);
		JSONArray json = new JSONArray(list);
		
		return json;
	}
	
}

package com.platform.frame.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtil {

	public static List getList(List list, String root) {

		List m_list = new ArrayList();

		getA(list, root, m_list);
		while (list.size() != 0) {
			mm(list, m_list);
		}
		return m_list;
	}

	public static void mm(List list, List m_list) {

		for (int i = 0; i < m_list.size(); i++) {
			List s_list = new ArrayList();
			Map map = (Map) m_list.get(i);
			if (map.get("children") == null) {
				String precode = String.valueOf(map.get("id"));
				getA(list, precode, s_list);
				map.put("children", s_list);
			} else {
				List i_list = (List) map.get("children");
				mm(list, i_list);
			}

		}
	}

	public static void getA(List list, String precode, List list1) {
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (precode.equals(String.valueOf(map.get("pid")))) {
				list.remove(i);
				i--;
				list1.add(map);
			}
		}
	}

	// [{"state":2,"children":[{"state":"1","children":[{"state":"1","precode":"4401","label":"珠海(人工站)","code":"59488","type":"M"}],"precode":"1","label":"人工站","code":"4401","type":null,"mx_internal_uid":"B36A0B23-FCBF-57A7-94E0-CA65CA0860AA"},{"state":2,"children":[{"state":0,"precode":"4402","label":"斗门（遥测站）","code":"58265","type":"Y","mx_internal_uid":"99D4503C-DB03-8AD7-5A54-CA65CFB9CAB5"},{"state":"1","precode":"4402","label":"平沙基地(遥测站)","code":"59487","type":"Y","mx_internal_uid":"F975AAE1-86CE-B860-B052-CA65CFB9EE76"},{"state":"1","precode":"4402","label":"珠海(遥测站)","code":"59488","type":"Y","mx_internal_uid":"80AF87C9-DC8E-0124-DC81-CA65CFB9960D"}],"precode":"1","label":"遥测站","code":"4402","type":null,"mx_internal_uid":"693FB646-EF97-AC46-CF15-CA65CA08A7BF"},{"state":"1","children":[{"state":"1","precode":"440402","label":"黄茅洲（中尺度）","code":"59681","type":"A"},{"state":"1","precode":"440402","label":"金鼎（中尺度）","code":"G1204","type":"A"},{"state":"1","precode":"440402","label":"广昌（中尺度）","code":"G1206","type":"A"},{"state":"1","precode":"440402","label":"九洲港（中尺度）","code":"G1209","type":"A"},{"state":"1","precode":"440402","label":"淇澳（中尺度）","code":"G1211","type":"A"},{"state":"1","precode":"440402","label":"拱北（中尺度）","code":"G1212","type":"A"},{"state":"1","precode":"440402","label":"九洲岛（中尺度）","code":"G1213","type":"A"},{"state":"1","precode":"440402","label":"淇澳桥顶（中尺度）","code":"G1215","type":"A"},{"state":"1","precode":"440402","label":"中心沟（中尺度）","code":"G1217","type":"A"},{"state":"1","precode":"440402","label":"香洲（520）","code":"G3800","type":"D"},{"state":"1","precode":"440402","label":"保税区（301）","code":"G3850","type":"B"},{"state":"1","precode":"440402","label":"横琴东（301）","code":"G3851","type":"B"},{"state":"1","precode":"440402","label":"翠微（301）","code":"G3852","type":"B"},{"state":"1","precode":"440402","label":"界涌（301）","code":"G3853","type":"B"},{"state":"1","precode":"440402","label":"下栅（301）","code":"G3854","type":"B"},{"state":"1","precode":"440402","label":"四中（301）","code":"G3857","type":"B"},{"state":"1","precode":"440402","label":"香洲港（301）","code":"G3859","type":"B"},{"state":"1","precode":"440402","label":"南屏（301）","code":"G3864","type":"B"},{"state":"1","precode":"440402","label":"唐家（301）","code":"G3865","type":"B"},{"state":"1","precode":"440402","label":"横琴西（510）","code":"G3905","type":"C"},{"state":"1","precode":"440402","label":"湾仔（510）","code":"G3908","type":"C"}],"precode":"1","label":"香洲区","code":"440402","type":null,"mx_internal_uid":"6EF1801F-E3AE-4486-4BB0-CA65CA08E77D"},{"state":"1","children":[{"state":"1","precode":"440403","label":"五山（中尺度）","code":"G1202","type":"A"},{"state":"1","precode":"440403","label":"井岸（中尺度）","code":"G1250","type":"A"},{"state":"1","precode":"440403","label":"上横（中尺度）","code":"G1251","type":"A"},{"state":"1","precode":"440403","label":"斗门镇（301）","code":"G3858","type":"B"},{"state":"1","precode":"440403","label":"六乡（510）","code":"G3907","type":"C"},{"state":"1","precode":"440403","label":"斗门（自动遥测站）","code":"GDDM","type":"F"}],"precode":"1","label":"斗门区","code":"440403","type":null,"mx_internal_uid":"8630A433-BA32-556F-F30D-CA65CA08E71E"},{"state":"1","children":[{"state":"1","precode":"440404","label":"机场（中尺度）","code":"G1205","type":"A"},{"state":"1","precode":"440404","label":"南水（中尺度）","code":"G1207","type":"A"},{"state":"1","precode":"440404","label":"平沙（中尺度）","code":"G1208","type":"A"},{"state":"1","precode":"440404","label":"红旗（中尺度）","code":"G1210","type":"A"},{"state":"1","precode":"440404","label":"校准站（中尺度）","code":"G1218","type":"A"},{"state":"1","precode":"440404","label":"珠海港（301）","code":"G3856","type":"B"},{"state":"1","precode":"440404","label":"三灶镇（301）","code":"G3860","type":"B"},{"state":"1","precode":"440404","label":"基地301（301）","code":"G3863","type":"B"},{"state":"1","precode":"440404","label":"小林（301）","code":"G3866","type":"B"},{"state":"1","precode":"440404","label":"基地510（510）","code":"G3900","type":"C"},{"state":"1","precode":"440404","label":"鹤洲北（510）","code":"G3904","type":"C"}],"precode":"1","label":"金湾区","code":"440404","type":null,"mx_internal_uid":"665ED481-4A49-BACB-3750-CA65CA08605A"},{"state":"1","children":[{"state":"1","precode":"440405","label":"桂山岛（中尺度）","code":"G1201","type":"A"},{"state":"1","precode":"440405","label":"庙湾（中尺度）","code":"G1216","type":"A"},{"state":"1","precode":"440405","label":"外伶仃（中尺度）","code":"G1279","type":"A"},{"state":"1","precode":"440405","label":"荷包岛（中尺度）","code":"G1280","type":"A"},{"state":"1","precode":"440405","label":"桂山电厂站（中尺度）","code":"G1281","type":"A"},{"state":"1","precode":"440405","label":"高栏岛（中尺度）","code":"G1282","type":"A"},{"state":"1","precode":"440405","label":"万山岛（中尺度）","code":"G1283","type":"A"},{"state":"1","precode":"440405","label":"内伶仃（中尺度）","code":"G1290","type":"A"},{"state":"1","precode":"440405","label":"东澳码头（海洋站）","code":"G3801","type":"E"},{"state":"1","precode":"440405","label":"高栏海（海洋站）","code":"G3802","type":"E"},{"state":"1","precode":"440405","label":"东澳（301）","code":"G3855","type":"B"},{"state":"1","precode":"440405","label":"外伶仃（301）","code":"G3861","type":"B"},{"state":"1","precode":"440405","label":"高栏西（301）","code":"G3862","type":"B"},{"state":"1","precode":"440405","label":"内伶仃（510）","code":"G3901","type":"C"},{"state":"1","precode":"440405","label":"万山（510）","code":"G3902","type":"C"},{"state":"1","precode":"440405","label":"高栏北（510）","code":"G3903","type":"C"}],"precode":"1","label":"海岛","code":"440405","type":null,"mx_internal_uid":"67E565FB-799F-8EF2-6B79-CA65CA08F478"},{"state":"1","children":[{"state":"1","precode":"440406","label":"市政公园（中尺度）","code":"G3950","type":"A"},{"state":"1","precode":"440406","label":"澳门污水厂（中尺度）","code":"G3951","type":"A"},{"state":"1","precode":"440406","label":"大炮台山（中尺度）","code":"G3952","type":"A"},{"state":"1","precode":"440406","label":"澳门教科文中心（中尺度）","code":"G3953","type":"A"},{"state":"1","precode":"440406","label":"海事博物馆（中尺度）","code":"G3954","type":"A"},{"state":"1","precode":"440406","label":"嘉乐庇总督大桥（中尺度）","code":"G3955","type":"A"},{"state":"1","precode":"440406","label":"友谊大桥北峰（中尺度）","code":"G3956","type":"A"},{"state":"1","precode":"440406","label":"西湾大桥（中尺度）","code":"G3957","type":"A"},{"state":"1","precode":"440406","label":"大潭山(观察坪)（中尺度）","code":"G3958","type":"A"},{"state":"1","precode":"440406","label":"大潭山(VAISALA)（中尺度）","code":"G3959","type":"A"},{"state":"1","precode":"440406","label":"凼仔市中心（中尺度）","code":"G3960","type":"A"},{"state":"1","precode":"440406","label":"九 澳 (VAISALA)（中尺度）","code":"G3961","type":"A"},{"state":"1","precode":"440406","label":"路环发电厂（中尺度）","code":"G3962","type":"A"}],"precode":"1","label":"澳门","code":"440406","type":null,"mx_internal_uid":"164F95DE-068D-7BA7-2F8C-CA65CA084DFB"}],"precode":"****","label":"所有站点","code":"1","type":null,"mx_internal_uid":"08973040-3725-7E14-C395-CA65CA057EEA"}]
	public static String getArray(List dataProvider) {
		if (dataProvider == null)
			return null;
		List<String> list = new ArrayList<String>();
		List<Map> awsList = new ArrayList<Map>();
		dd(awsList, dataProvider);
//		String[] awsArray = awsList.toArray(new String[awsList.size()]);
		String awsStr = "('','')";
		if(!awsList.isEmpty()){
			awsStr = "";
			for (int i = 0; i < awsList.size() - 1; i++) {
				Map map = awsList.get(i);
				awsStr += "('" + map.get("code") + "' , '" + map.get("type") + "') ,";
			}
			Map map = awsList.get(awsList.size() - 1);
			awsStr += "('" + map.get("code") + "' , '" + map.get("type") + "') ";
//			awsStr = awsStr.replaceAll(",", "','");
		}
		
//		list.add(awsStr);
		return awsStr;
	}
	
	public static void dd(List<Map> awsList, List dataProvider){
		for (int i = 0; i < dataProvider.size(); i++) {
			Map map = (Map) dataProvider.get(i);
			if ("0".equals(String.valueOf(map.get("state"))))
				continue;
			List child = (List) map.get("children");
			if (child != null && !child.isEmpty())
				dd(awsList, child);

//			if ("A".equals(map.get("type")) || "B".equals(map.get("type"))
//					|| "C".equals(map.get("type"))) {
				awsList.add(map);
//			}
		}
	}
	
}

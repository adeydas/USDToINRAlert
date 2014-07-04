package ws.abhis.utils.CurrencyInfo;

import java.util.List;

public class VisualizationTemplate {
	private String template = "<html>\n"
					+ "<head>\n"
					+ "<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n"
					+ "<script type=\"text/javascript\">\n"
					+ "google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});\n"
					+ "google.setOnLoadCallback(drawChart);\n"
					+ "function drawChart() {\n"
					+ "var data = google.visualization.arrayToDataTable([\n"
					+ "['Count', 'Rate'],\n";
					
	
	private String template1 =  "]);\n"

        			+ "var options = {\n"
        			+ "title: 'Conversion rate chart'"
        			+ "};\n"

        			+ "var chart = new google.visualization.LineChart(document.getElementById('chart_div'));\n"
        			+ "chart.draw(data, options);\n"
      				+ "}\n"
      				+ "</script>\n"
      				+ "</head>\n"
      				+ "<body>\n"
      				+ "<div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>\n"
      				+ "</body>\n"
      				+ "</html>";
	
	public String createVisualizationHtml(AllCurrencyData data) {
		List<CurrencyType> allCurrs = data.allData;
		String ret = "";
		for (int i=0; i<allCurrs.size(); i++) {
			ret += "[";
			ret += Integer.toString(i) + ", ";
			ret += Double.toString(allCurrs.get(i).getRate());
			ret += "],\n";
		}
		ret = ret.substring(0, ret.length()-1);
		String finalHtml = template + ret + template1;
		return finalHtml;
	}
}

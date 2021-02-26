package alma.exec.acsclient.acsclientctl.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class AcsClientController {
	
	private String componentJSON;
	private String containerJSON;
		
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping(value = "/acs-status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String acsStatus() {
		return "\"Status for ACS instance: 0\\n------------------------------------------------------------------------\\nManager process ID is: 17261\\nNaming service process ID is: 4398\\nNotify service process ID is: 7776\\nLogging service process ID is: 15575\\nIFR process ID is: 12386\\nLogging notify service process ID is: 15144\\nArchive notify service process ID is: 6586\\nAlarm notify service process ID is: 14716\\nACS Log Service process ID is: 16700\\nACS CDB process ID is: 9486\\nACS Alarm Service process ID is: 18570\\n\\nEmitting information about all known local and remote containers:\\nThe ARCHIVE/ACC/cppTotalPowerContainer container is running on 127.0.0.1 using the TCP port: 4000\\nThe ACC/masterContainer container is running on 127.0.0.1 using the TCP port: 4002\\n\\nEmitting information about all known local and remote notify services:\\nThe AcaNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe Aca notify service is running on 127.0.0.1 using the TCP port: 3021\\nThe CorrNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe Corr notify service is running on 127.0.0.1 using the TCP port: 3023\\nThe ControlSystemNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe ControlSystem notify service is running on 127.0.0.1 using the TCP port: 3025\\nThe TelcalNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe Telcal notify service is running on 127.0.0.1 using the TCP port: 3027\\nThe NotifyEventChannelFactoryPM notify service is running on 127.0.0.1 using the TCP port: 3028\\nThe DcNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe Dc notify service is running on 127.0.0.1 using the TCP port: 3030\\nThe NotifyEventChannelFactoryDA notify service is running on 127.0.0.1 using the TCP port: 3031\\nThe SchedulingNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe Scheduling notify service is running on 127.0.0.1 using the TCP port: 3033\\nThe NotifyEventChannelFactoryDV notify service is running on 127.0.0.1 using the TCP port: 3034\\nThe ControlRealtimeNotifyEventChannelFactory notify service is not currently running but was at some point in time.\\nThe ControlRealtime notify service is running on 127.0.0.1 using the TCP port: 3036\\nThe NotifyEventChannelFactoryCM notify service is running on 127.0.0.1 using the TCP port: 3037\\n------------------------------------------------------------------------\\n\"";
	}
	
	@RequestMapping(value = "/display-subsystems", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String displaySubsystems() {
		return "["
				+ "{\"name\":\"SCHEDULING\",\"clazz\":\"alma.exec.Subsystem\",\"components\":[],\"masterComponent\":\"SCHEDULING_MASTER_COMP\"},"
				+ "{\"name\":\"CONTROL\",\"clazz\":\"alma.exec.Subsystem\",\"components\":[\"CONTROL/ACC/javaContainer\"],\"masterComponent\":\"CONTROL_MASTER_COMP\"}"
				+ "]";
	}

	@RequestMapping(value = "/display-tasks", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String displayTasks() {
		return "[{\"name\":\"SubsystemListTask\",\"complete\":true,\"id\":555871116,\"startTime\":1614321506499,\"parameters\":\"\",\"exception\":null,\"hasResult\":true},{\"name\":\"SubsystemListTask\",\"complete\":true,\"id\":713244833,\"startTime\":1614321458118,\"parameters\":\"\",\"exception\":null,\"hasResult\":true},{\"name\":\"SearchComponentsTask\",\"complete\":true,\"id\":876429962,\"startTime\":1614320363985,\"parameters\":\"query: CONTROL**; \",\"exception\":null,\"hasResult\":true},{\"name\":\"SubsystemListTask\",\"complete\":true,\"id\":18062983,\"startTime\":1614320363985,\"parameters\":\"\",\"exception\":null,\"hasResult\":true},{\"name\":\"AcsStatusTask\",\"complete\":true,\"id\":1869556533,\"startTime\":1614320363978,\"parameters\":\"\",\"exception\":null,\"hasResult\":true},{\"name\":\"ContainerListTask\",\"complete\":true,\"id\":1171800541,\"startTime\":1614320363976,\"parameters\":\"\",\"exception\":null,\"hasResult\":true}]";
	}

	@RequestMapping(value = "/display-tmcdb-containers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String displayTmcdbContainers() {
		if (containerJSON == null) {
			File f = null;
			StringBuffer buff = new StringBuffer();
			try {
				URL resource = getClass().getClassLoader().getResource("containers-mock-data.txt");
				f = new File(resource.toURI());
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					buff.append(line);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			containerJSON = buff.toString();
		}
		return containerJSON;
	}

	@RequestMapping(value = "/search-components", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String searchComponents(@RequestParam(name="query", required=true, defaultValue="*") String query) {

		if (componentJSON == null) {
			File f = null;
			StringBuffer buff = new StringBuffer();
			try {
				URL resource = getClass().getClassLoader().getResource("components-mock-data.txt");
				f = new File(resource.toURI());
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					buff.append(line);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			componentJSON = buff.toString();
		}
		return componentJSON;
	}

	

}

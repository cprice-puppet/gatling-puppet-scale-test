package com.puppetlabs.gatling.node_simulations
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
// import io.gatling.jdbc.Predef._

class OpsWorksSmall extends SimulationWithScenario {

// 	val httpProtocol = http
// 		.baseURL("https://aws-mom.us-west-2.compute.internal:8140")

	val reportBody = ElFileBody("OpsWorksSmall_0005_request.txt")

	val headers_0 = Map("X-Puppet-Version" -> "4.10.4")

	val headers_3 = Map(
		"Accept" -> "pson, dot, binary",
		"X-Puppet-Version" -> "4.10.4")

	val headers_5 = Map(
		"Accept" -> "pson",
		"Content-Type" -> "text/pson",
		"X-Puppet-Version" -> "4.10.4",
		"Connection" -> "close")
//
// val uri1 = "https://aws-mom.us-west-2.compute.internal:8140/puppet/v3"

	val scn = scenario("OpsWorksSmall")
		.exec(http("node")
			.get("/puppet/v3/node/${node}?environment=production&transaction_uuid=52aa1f3a-a1f5-437d-967c-8e3af99db70b&fail_on_404=true")
			.headers(headers_0))
		.exec(http("filemeta pluginfacts")
			.get("/puppet/v3/file_metadatas/pluginfacts?environment=production&links=follow&recurse=true&source_permissions=use&ignore=.svn&ignore=CVS&ignore=.git&ignore=.hg&checksum_type=md5")
			.headers(headers_0))
		.pause(1)
		.exec(http("filemeta plugins")
			.get("/puppet/v3/file_metadatas/plugins?environment=production&links=follow&recurse=true&source_permissions=ignore&ignore=.svn&ignore=CVS&ignore=.git&ignore=.hg&checksum_type=md5")
			.headers(headers_0))
		.pause(2)
		.exec(http("catalog")
			.post("/puppet/v3/catalog/${node}?environment=production")
			.headers(headers_3)
			.formParam("environment", "production")
			.formParam("facts_format", "pson")
			.formParam("facts", "%7B%22name%22%3A%22${node}%22%2C%22values%22%3A%7B%22aio_agent_build%22%3A%221.10.4%22%2C%22aio_agent_version%22%3A%221.10.4%22%2C%22architecture%22%3A%22x86_64%22%2C%22augeas%22%3A%7B%22version%22%3A%221.4.0%22%7D%2C%22augeasversion%22%3A%221.4.0%22%2C%22bios_release_date%22%3A%2202%2F16%2F2017%22%2C%22bios_vendor%22%3A%22Xen%22%2C%22bios_version%22%3A%224.2.amazon%22%2C%22blockdevice_xvda_size%22%3A8589934592%2C%22blockdevices%22%3A%22xvda%22%2C%22chassistype%22%3A%22Other%22%2C%22choco_install_path%22%3A%22C%3A%5C%5CProgramData%5C%5Cchocolatey%22%2C%22chocolateyversion%22%3A%220%22%2C%22dhcp_servers%22%3A%7B%22eth0%22%3A%22172.31.16.1%22%2C%22system%22%3A%22172.31.16.1%22%7D%2C%22disks%22%3A%7B%22xvda%22%3A%7B%22size%22%3A%228.00+GiB%22%2C%22size_bytes%22%3A8589934592%7D%7D%2C%22dmi%22%3A%7B%22bios%22%3A%7B%22release_date%22%3A%2202%2F16%2F2017%22%2C%22vendor%22%3A%22Xen%22%2C%22version%22%3A%224.2.amazon%22%7D%2C%22chassis%22%3A%7B%22type%22%3A%22Other%22%7D%2C%22manufacturer%22%3A%22Xen%22%2C%22product%22%3A%7B%22name%22%3A%22HVM+domU%22%2C%22serial_number%22%3A%22ec240e61-a376-d9f2-45fc-bf33b16bbe3c%22%2C%22uuid%22%3A%22EC240E61-A376-D9F2-45FC-BF33B16BBE3C%22%7D%7D%2C%22domain%22%3A%22us-west-2.compute.internal%22%2C%22ec2_metadata%22%3A%7B%22ami-id%22%3A%22ami-e9503589%22%2C%22ami-launch-index%22%3A%220%22%2C%22ami-manifest-path%22%3A%22%28unknown%29%22%2C%22block-device-mapping%22%3A%7B%22ami%22%3A%22%2Fdev%2Fsda1%22%2C%22root%22%3A%22%2Fdev%2Fsda1%22%7D%2C%22hostname%22%3A%22ip-172-31-20-16.us-west-2.compute.internal%22%2C%22instance-action%22%3A%22none%22%2C%22instance-id%22%3A%22i-05538bfaf3b1c37b7%22%2C%22instance-type%22%3A%22c4.large%22%2C%22local-hostname%22%3A%22ip-172-31-20-16.us-west-2.compute.internal%22%2C%22local-ipv4%22%3A%22172.31.20.16%22%2C%22mac%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22metrics%22%3A%7B%22vhostmd%22%3A%22%3C%3Fxml+version%3D%5C%221.0%5C%22+encoding%3D%5C%22UTF-8%5C%22%3F%3E%22%7D%2C%22network%22%3A%7B%22interfaces%22%3A%7B%22macs%22%3A%7B%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%3A%7B%22device-number%22%3A%220%22%2C%22interface-id%22%3A%22eni-1519db2a%22%2C%22ipv4-associations%22%3A%7B%2252.89.85.243%22%3A%22172.31.20.16%22%7D%2C%22local-hostname%22%3A%22ip-172-31-20-16.us-west-2.compute.internal%22%2C%22local-ipv4s%22%3A%22172.31.20.16%22%2C%22mac%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22owner-id%22%3A%22028918822489%22%2C%22public-hostname%22%3A%22ec2-52-89-85-243.us-west-2.compute.amazonaws.com%22%2C%22public-ipv4s%22%3A%2252.89.85.243%22%2C%22security-group-ids%22%3A%22sg-236e6058%5Cnsg-41b94725%5Cnsg-8fe7f3f4%5Cnsg-a3967fd9%22%2C%22security-groups%22%3A%22Office-only-ports%5Cnbeaker-ping%5CnPlatform+Test+Group%5Cndrosser+ssh+from+home%22%2C%22subnet-id%22%3A%22subnet-60ca2317%22%2C%22subnet-ipv4-cidr-block%22%3A%22172.31.16.0%2F20%22%2C%22vpc-id%22%3A%22vpc-bb9866de%22%2C%22vpc-ipv4-cidr-block%22%3A%22172.31.0.0%2F16%22%2C%22vpc-ipv4-cidr-blocks%22%3A%22172.31.0.0%2F16%22%7D%7D%7D%7D%2C%22placement%22%3A%7B%22availability-zone%22%3A%22us-west-2a%22%7D%2C%22product-codes%22%3A%226x5jmcajty9edm3f211pqjfn2%22%2C%22profile%22%3A%22default-hvm%22%2C%22public-hostname%22%3A%22ec2-52-89-85-243.us-west-2.compute.amazonaws.com%22%2C%22public-ipv4%22%3A%2252.89.85.243%22%2C%22public-keys%22%3A%7B%220%22%3A%7B%22openssh-key%22%3A%22ssh-rsa+AAAAB3NzaC1yc2EAAAADAQABAAACAQDAO2ao8gis26WKiJa%2FlQDvNz0S%2B7kOxNczo8ZZgFk0r7u5%2BNL5jtP5q3tswNcd70gcjZM01cPKL%2FUViycKLWb14qi8wV4J4%2FBnd2oxNnhy%2FlzgmpLvwdU3d1qf0bSIjc1uaT20WIzbF4W7M6TRkIxbQSyuqv7GGUy%2BLm%2BFpxor6mQDpbLx90txS7fWCljF9zNpY%2BMDb1gayEAUPNN43XgTVJ%2BeD4kjxzF1TL6t5aqEQp46V6xLgjroAI5EhE1qu0i9v6VLKI1CVGljN3nqw%2FEF9wykqD41vk3uzsGU4xHTQmNvAxrc1M9BjzUD9g8WVCJAmV8eEaR8K0uMVdtyzOxBRv6qF6LQ%2BFlAh5SwCJ8tJ248BriOakV6PxbvqWxRb3Y%2FJMmAnWzmEaOZ5TYLORKUEULmz3Y6Qi6B3cRF6C%2FWVvi%2BUmmKVdKhtYxyrMwW%2FSV1jYJ5Cg4Eec8Yqg%2Bs7lD%2FKtFsjErddZ%2FoObNtINq23LaBF%2BAy%2FIL%2FpjieEJ3KI6ZJwD6qR3AxoZ8G43QgtLZDbGz8fvZcqS3VvN4bWZoepEcFY%2BfHrfTcFwEbetQOx06e5fElbvjPIkDiNm%2FdKo6uuuhbPIgbX9EnOicd4nqh4TZglgc13rleWEsgL6p9%2FUhw1ARDCiYppqVmWUl6mP1%2FpsQ3TKKdMwvs%2FonFa6DMJw%3D%3D+Beaker-drosser-fogdor-9424083048-2017-07-04_11_20_36_241402480%22%7D%7D%2C%22reservation-id%22%3A%22r-0ac483ea4d0782eab%22%2C%22security-groups%22%3A%22Office-only-ports%5Cnbeaker-ping%5CnPlatform+Test+Group%5Cndrosser+ssh+from+home%22%2C%22services%22%3A%7B%22domain%22%3A%22amazonaws.com%22%2C%22partition%22%3A%22aws%22%7D%7D%2C%22facterversion%22%3A%223.6.5%22%2C%22fake_domain%22%3A%22pgtomcat.mycompany.org%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22fqdn%22%3A%22${node}%22%2C%22function%22%3A%22app%22%2C%22gid%22%3A%22root%22%2C%22group%22%3A%22pgtomcat%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22aws-agent-01%22%2C%22id%22%3A%22root%22%2C%22identity%22%3A%7B%22gid%22%3A0%2C%22group%22%3A%22root%22%2C%22privileged%22%3Atrue%2C%22uid%22%3A0%2C%22user%22%3A%22root%22%7D%2C%22interfaces%22%3A%22eth0%2Clo%22%2C%22ip6tables_version%22%3A%221.4.7%22%2C%22ipaddress%22%3A%22172.31.20.16%22%2C%22ipaddress6%22%3A%22fe80%3A%3A497%3A27ff%3Afedb%3Aa7a4%22%2C%22ipaddress6_eth0%22%3A%22fe80%3A%3A497%3A27ff%3Afedb%3Aa7a4%22%2C%22ipaddress6_lo%22%3A%22%3A%3A1%22%2C%22ipaddress_eth0%22%3A%22172.31.20.16%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22iptables_version%22%3A%221.4.7%22%2C%22is_pe%22%3Afalse%2C%22is_virtual%22%3Atrue%2C%22java_default_home%22%3A%22%2Fusr%2Flib%2Fjvm%2Fjava-1.7.0-openjdk-1.7.0.141.x86_64%22%2C%22java_libjvm_path%22%3A%22%2Fusr%2Flib%2Fjvm%2Fjava-1.7.0-openjdk-1.7.0.141.x86_64%2Fjre%2Flib%2Famd64%2Fserver%22%2C%22java_major_version%22%3A%227%22%2C%22java_patch_level%22%3A%22141%22%2C%22java_version%22%3A%221.7.0_141%22%2C%22jenkins_plugins%22%3A%22%22%2C%22kernel%22%3A%22Linux%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22kernelrelease%22%3A%222.6.32-696.1.1.el6.x86_64%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22load_averages%22%3A%7B%2215m%22%3A0.03%2C%221m%22%3A0.09%2C%225m%22%3A0.04%7D%2C%22macaddress%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22macaddress_eth0%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22manufacturer%22%3A%22Xen%22%2C%22memory%22%3A%7B%22system%22%3A%7B%22available%22%3A%223.12+GiB%22%2C%22available_bytes%22%3A3350953984%2C%22capacity%22%3A%2212.24%25%22%2C%22total%22%3A%223.56+GiB%22%2C%22total_bytes%22%3A3818323968%2C%22used%22%3A%22445.72+MiB%22%2C%22used_bytes%22%3A467369984%7D%7D%2C%22memoryfree%22%3A%223.12+GiB%22%2C%22memoryfree_mb%22%3A3195.71875%2C%22memorysize%22%3A%223.56+GiB%22%2C%22memorysize_mb%22%3A3641.4375%2C%22mountpoints%22%3A%7B%22%2F%22%3A%7B%22available%22%3A%226.57+GiB%22%2C%22available_bytes%22%3A7054508032%2C%22capacity%22%3A%2215.21%25%22%2C%22device%22%3A%22%2Fdev%2Fxvda1%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%227.75+GiB%22%2C%22size_bytes%22%3A8319852544%2C%22used%22%3A%221.18+GiB%22%2C%22used_bytes%22%3A1265344512%7D%2C%22%2Fdev%2Fshm%22%3A%7B%22available%22%3A%221.78+GiB%22%2C%22available_bytes%22%3A1909161984%2C%22capacity%22%3A%220%25%22%2C%22device%22%3A%22tmpfs%22%2C%22filesystem%22%3A%22tmpfs%22%2C%22options%22%3A%5B%22rw%22%2C%22rootcontext%3D%5C%22system_u%3Aobject_r%3Atmpfs_t%3As0%5C%22%22%5D%2C%22size%22%3A%221.78+GiB%22%2C%22size_bytes%22%3A1909161984%2C%22used%22%3A%220+bytes%22%2C%22used_bytes%22%3A0%7D%7D%2C%22mtu_eth0%22%3A9001%2C%22mtu_lo%22%3A65536%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_eth0%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_lo%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22netmask_eth0%22%3A%22255.255.240.0%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22network%22%3A%22172.31.16.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22network6_eth0%22%3A%22fe80%3A%3A%22%2C%22network6_lo%22%3A%22%3A%3A1%22%2C%22network_eth0%22%3A%22172.31.16.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22networking%22%3A%7B%22dhcp%22%3A%22172.31.16.1%22%2C%22domain%22%3A%22us-west-2.compute.internal%22%2C%22fqdn%22%3A%22${node}%22%2C%22hostname%22%3A%22aws-agent-01%22%2C%22interfaces%22%3A%7B%22eth0%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22172.31.20.16%22%2C%22netmask%22%3A%22255.255.240.0%22%2C%22network%22%3A%22172.31.16.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22fe80%3A%3A497%3A27ff%3Afedb%3Aa7a4%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22fe80%3A%3A%22%7D%5D%2C%22dhcp%22%3A%22172.31.16.1%22%2C%22ip%22%3A%22172.31.20.16%22%2C%22ip6%22%3A%22fe80%3A%3A497%3A27ff%3Afedb%3Aa7a4%22%2C%22mac%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22mtu%22%3A9001%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22172.31.16.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%7D%2C%22lo%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22127.0.0.1%22%2C%22netmask%22%3A%22255.0.0.0%22%2C%22network%22%3A%22127.0.0.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22%3A%3A1%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22%3A%3A1%22%7D%5D%2C%22ip%22%3A%22127.0.0.1%22%2C%22ip6%22%3A%22%3A%3A1%22%2C%22mtu%22%3A65536%2C%22netmask%22%3A%22255.0.0.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22127.0.0.0%22%2C%22network6%22%3A%22%3A%3A1%22%7D%7D%2C%22ip%22%3A%22172.31.20.16%22%2C%22ip6%22%3A%22fe80%3A%3A497%3A27ff%3Afedb%3Aa7a4%22%2C%22mac%22%3A%2206%3A97%3A27%3Adb%3Aa7%3Aa4%22%2C%22mtu%22%3A9001%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22172.31.16.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22primary%22%3A%22eth0%22%7D%2C%22operatingsystem%22%3A%22CentOS%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22operatingsystemrelease%22%3A%226.9%22%2C%22os%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22family%22%3A%22RedHat%22%2C%22hardware%22%3A%22x86_64%22%2C%22name%22%3A%22CentOS%22%2C%22release%22%3A%7B%22full%22%3A%226.9%22%2C%22major%22%3A%226%22%2C%22minor%22%3A%229%22%7D%2C%22selinux%22%3A%7B%22config_mode%22%3A%22enforcing%22%2C%22current_mode%22%3A%22enforcing%22%2C%22enabled%22%3Atrue%2C%22enforced%22%3Atrue%2C%22policy_version%22%3A%2224%22%7D%7D%2C%22osfamily%22%3A%22RedHat%22%2C%22package_provider%22%3A%22yum%22%2C%22partitions%22%3A%7B%22%2Fdev%2Fxvda1%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2F%22%2C%22size%22%3A%228.00+GiB%22%2C%22size_bytes%22%3A8588886016%2C%22uuid%22%3A%22b48e599f-bd30-4876-9a63-ecd015ba7454%22%7D%7D%2C%22path%22%3A%22PATH%3A%2Fopt%2Fpuppetlabs%2Fbin%3A%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22pe_concat_basedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%2Fpe_concat%22%2C%22pe_razor_server_version%22%3A%22package+pe-razor-server+is+not+installed%22%2C%22physicalprocessorcount%22%3A1%2C%22platform_symlink_writable%22%3Atrue%2C%22platform_tag%22%3A%22el-6-x86_64%22%2C%22processor0%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E5-2666+v3+%40+2.90GHz%22%2C%22processor1%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E5-2666+v3+%40+2.90GHz%22%2C%22processorcount%22%3A2%2C%22processors%22%3A%7B%22count%22%3A2%2C%22isa%22%3A%22x86_64%22%2C%22models%22%3A%5B%22Intel%28R%29+Xeon%28R%29+CPU+E5-2666+v3+%40+2.90GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E5-2666+v3+%40+2.90GHz%22%5D%2C%22physicalcount%22%3A1%7D%2C%22productname%22%3A%22HVM+domU%22%2C%22puppet_files_dir_present%22%3Afalse%2C%22puppet_inventory_metadata%22%3A%7B%22packages%22%3A%7B%22collection_enabled%22%3Afalse%2C%22last_collection_time%22%3A%220.0s%22%7D%7D%2C%22puppet_vardir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%22%2C%22puppetversion%22%3A%224.10.4%22%2C%22root_home%22%3A%22%2Froot%22%2C%22rsyslog_version%22%3A%225.8.10%22%2C%22ruby%22%3A%7B%22platform%22%3A%22x86_64-linux%22%2C%22sitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22version%22%3A%222.1.9%22%7D%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22rubyversion%22%3A%222.1.9%22%2C%22selinux%22%3Atrue%2C%22selinux_config_mode%22%3A%22enforcing%22%2C%22selinux_current_mode%22%3A%22enforcing%22%2C%22selinux_enforced%22%3Atrue%2C%22selinux_policyversion%22%3A%2224%22%2C%22serialnumber%22%3A%22ec240e61-a376-d9f2-45fc-bf33b16bbe3c%22%2C%22service_provider%22%3A%22redhat%22%2C%22ssh%22%3A%7B%22dsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+2+1+2c32fe9cfec77a10219751dfcc593d2b7dd828de%22%2C%22sha256%22%3A%22SSHFP+2+2+8a77f7d23c4b1e862c7980c79fdb5fb1e294350bf3a16e37471af53ae8bb6d21%22%7D%2C%22key%22%3A%22AAAAB3NzaC1kc3MAAACBAJwIf%2BttOFNBJGuGE2Y%2FIS5WR0SwE%2F%2B6sHOJdwrtbmzk8XzTjwf7AWw8AwZjKrXu1MMGAHp6JbTWhdNVwJyiJuXLFa14wvDfRzVCgb0%2BMJ7X8%2Fz3yO%2FJADlI7xFoIaOPCLHsB9nE13FQmWkwv3x6huZZe9%2FMQFctPlFnzL6NFHXNAAAAFQD8c4T8SME16Lj7BxJaYaRi%2F7iylwAAAIB2NdKn%2FUcSMNjH95R1EomznsklZ4anYhDiyDaszKB1XG%2BeLFojyC323h%2FZfgIi8vZfa6CLoNrFVtb%2F7P%2FInMCTTzmEDXh3ZIedaUv33uo9nNdqXdyPtMkg%2Fw4F%2BrmCjn0W%2BbclJN6Q8DdIDNZGcnh00Dp0Q1gvI6DJs1aalC2LRAAAAIB1QOSP%2FSu5mgTrUNEOs1DRaV4Ou2%2FsleyTTphxwqRSvYachcgg2cnSXfqtbPQaY3Eii%2BOz6hvRf1Q%2BeRwbIkgZ64wbXFr6s40KhzBGmbNFe7oms62aLsalgs2LWj30wjPeorJVLWce1Hzgyc1xwl7RmajL6SgN9POW3D7rLU4Z%2Fw%3D%3D%22%7D%2C%22rsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+1+1+87c2c2f7cdf1b5538d38c8a64906451ccdf7d9a8%22%2C%22sha256%22%3A%22SSHFP+1+2+e2f329abb4c0018a5d15b22cc53c5c8a3e9b542146aef9e0f54697d5f2fa168e%22%7D%2C%22key%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAvrIJDP9Ij1GODYsbDguqU8Dt90vpl5xtGyUhk9WmcxwMEWz%2FDASbGYYO%2FjW5pnpD7UBnCm08L2JB7GJb4LB9ACg3HkwjdpPb7TRx90xVyh%2FCjbUIp5sITY2AyZfcv5IZbdCmTLLPMNtUAVa04iJ%2BDLoQzeOnXWzC5Zp%2FVRupikWLc29zs6Xt54jhpBNToptgRDcgJht%2F9JRmjdD%2FIxxT2SUksbO1gVK%2F1rUbXimfMeeISIW1Xe3%2F%2BSkmTHoiBth5VW752A7sAtSQ78nEICOEo4nBuNTf3n2FmWGrmgRaewZtcaUN6T4jMwwI5552SfT0eWx7TlpMES0g9ZKuRICDaw%3D%3D%22%7D%7D%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAJwIf%2BttOFNBJGuGE2Y%2FIS5WR0SwE%2F%2B6sHOJdwrtbmzk8XzTjwf7AWw8AwZjKrXu1MMGAHp6JbTWhdNVwJyiJuXLFa14wvDfRzVCgb0%2BMJ7X8%2Fz3yO%2FJADlI7xFoIaOPCLHsB9nE13FQmWkwv3x6huZZe9%2FMQFctPlFnzL6NFHXNAAAAFQD8c4T8SME16Lj7BxJaYaRi%2F7iylwAAAIB2NdKn%2FUcSMNjH95R1EomznsklZ4anYhDiyDaszKB1XG%2BeLFojyC323h%2FZfgIi8vZfa6CLoNrFVtb%2F7P%2FInMCTTzmEDXh3ZIedaUv33uo9nNdqXdyPtMkg%2Fw4F%2BrmCjn0W%2BbclJN6Q8DdIDNZGcnh00Dp0Q1gvI6DJs1aalC2LRAAAAIB1QOSP%2FSu5mgTrUNEOs1DRaV4Ou2%2FsleyTTphxwqRSvYachcgg2cnSXfqtbPQaY3Eii%2BOz6hvRf1Q%2BeRwbIkgZ64wbXFr6s40KhzBGmbNFe7oms62aLsalgs2LWj30wjPeorJVLWce1Hzgyc1xwl7RmajL6SgN9POW3D7rLU4Z%2Fw%3D%3D%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+2c32fe9cfec77a10219751dfcc593d2b7dd828de%5CnSSHFP+2+2+8a77f7d23c4b1e862c7980c79fdb5fb1e294350bf3a16e37471af53ae8bb6d21%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+87c2c2f7cdf1b5538d38c8a64906451ccdf7d9a8%5CnSSHFP+1+2+e2f329abb4c0018a5d15b22cc53c5c8a3e9b542146aef9e0f54697d5f2fa168e%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAvrIJDP9Ij1GODYsbDguqU8Dt90vpl5xtGyUhk9WmcxwMEWz%2FDASbGYYO%2FjW5pnpD7UBnCm08L2JB7GJb4LB9ACg3HkwjdpPb7TRx90xVyh%2FCjbUIp5sITY2AyZfcv5IZbdCmTLLPMNtUAVa04iJ%2BDLoQzeOnXWzC5Zp%2FVRupikWLc29zs6Xt54jhpBNToptgRDcgJht%2F9JRmjdD%2FIxxT2SUksbO1gVK%2F1rUbXimfMeeISIW1Xe3%2F%2BSkmTHoiBth5VW752A7sAtSQ78nEICOEo4nBuNTf3n2FmWGrmgRaewZtcaUN6T4jMwwI5552SfT0eWx7TlpMES0g9ZKuRICDaw%3D%3D%22%2C%22stage%22%3A%22prod%22%2C%22staging_http_get%22%3A%22curl%22%2C%22system_uptime%22%3A%7B%22days%22%3A3%2C%22hours%22%3A76%2C%22seconds%22%3A275995%2C%22uptime%22%3A%223+days%22%7D%2C%22timezone%22%3A%22UTC%22%2C%22uptime%22%3A%223+days%22%2C%22uptime_days%22%3A3%2C%22uptime_hours%22%3A76%2C%22uptime_seconds%22%3A275995%2C%22uuid%22%3A%22EC240E61-A376-D9F2-45FC-BF33B16BBE3C%22%2C%22virtual%22%3A%22xenhvm%22%2C%22whereami%22%3A%22portland%22%2C%22clientcert%22%3A%22${node}%22%2C%22clientversion%22%3A%224.10.4%22%2C%22clientnoop%22%3Afalse%7D%2C%22timestamp%22%3A%222017-07-07T23%3A00%3A57.934702036%2B00%3A00%22%2C%22expiration%22%3A%222125-07-07T23%3A30%3A57.934985879%2B00%3A00%22%7D")
			.formParam("transaction_uuid", "52aa1f3a-a1f5-437d-967c-8e3af99db70b")
			.formParam("static_catalog", "true")
			.formParam("checksum_type", "md5.sha256")
			.formParam("fail_on_404", "true"))
		.pause(1)
		.exec(http("filemeta mco plugins")
			.get("/puppet/v3/file_metadatas/modules/puppet_enterprise/mcollective/plugins?environment=production&links=manage&recurse=true&source_permissions=ignore&checksum_type=md5")
			.headers(headers_0))
		.pause(1)
		.exec((session:Session) => {
			session.set("reportTimestamp",
				LocalDateTime.now.toString(ISODateTimeFormat.dateTime()))
		})
		.exec((session:Session) => {
			session.set("transactionUuid",
				UUID.randomUUID().toString())
		})
		.exec(http("report")
			.put("/puppet/v3/report/${node}?environment=production&")
			.headers(headers_5)
			.body(reportBody))

// setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
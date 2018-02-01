package com.puppetlabs.gatling.node_simulations
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
// import io.gatling.jdbc.Predef._

class HoytLargeRC10 extends SimulationWithScenario {

// 	val httpProtocol = http
// 		.baseURL("https://ec2-34-217-73-7.us-west-2.compute.amazonaws.com:8140")
// 		.acceptHeader("application/json, text/pson")
// 		.acceptEncodingHeader("gzip;q=1.0,deflate;q=0.6,identity;q=0.3")
// 		.userAgentHeader("Puppet/5.3.0 Ruby/2.4.1-p111 (x86_64-linux)")

	val reportBody = ElFileBody("HoytLargeRC10_0005_request.txt")

	val baseHeaders = Map("Accept" -> "application/json, text/pson",
		"Accept-Encoding" -> "gzip;q=1.0,deflate;q=0.6,identity;q=0.3",
		"User-Agent" -> "Puppet/5.3.0 Ruby/2.4.1-p111 (x86_64-linux)")

	val headers_0 = baseHeaders ++ Map("X-Puppet-Version" -> "5.3.0")

	val headers_5 = baseHeaders ++ Map(
		"Connection" -> "close",
		"Content-Type" -> "application/json",
		"X-Puppet-Version" -> "5.3.0")
// val uri1 = "https://ec2-34-217-73-7.us-west-2.compute.amazonaws.com:8140/puppet/v3"

	val scn = scenario("HoytLargeRC10")
		.exec(http("node")
			.get("/puppet/v3/node/${node}?environment=production&transaction_uuid=83e3ab94-028c-4331-9708-7a1fef8ef242&fail_on_404=true")
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
			.headers(headers_0)
			.formParam("environment", "production")
			.formParam("facts_format", "application/json")
			.formParam("facts", "%7B%22name%22%3A%22${node}%22%2C%22values%22%3A%7B%22aio_agent_build%22%3A%225.2.0.84.g697715f%22%2C%22aio_agent_version%22%3A%225.2.0.84%22%2C%22apache_version%22%3A%222.4.6%22%2C%22architecture%22%3A%22x86_64%22%2C%22augeas%22%3A%7B%22version%22%3A%221.8.1%22%7D%2C%22augeasversion%22%3A%221.8.1%22%2C%22bios_release_date%22%3A%2208%2F24%2F2006%22%2C%22bios_vendor%22%3A%22Xen%22%2C%22bios_version%22%3A%224.2.amazon%22%2C%22blockdevice_xvda_size%22%3A53687091200%2C%22blockdevices%22%3A%22xvda%22%2C%22chassistype%22%3A%22Other%22%2C%22choco_install_path%22%3A%22C%3A%5C%5CProgramData%5C%5Cchocolatey%22%2C%22chocolateyversion%22%3A%220%22%2C%22dhcp_servers%22%3A%7B%22eth0%22%3A%22172.31.32.1%22%2C%22system%22%3A%22172.31.32.1%22%7D%2C%22disks%22%3A%7B%22xvda%22%3A%7B%22size%22%3A%2250.00%20GiB%22%2C%22size_bytes%22%3A53687091200%7D%7D%2C%22dmi%22%3A%7B%22bios%22%3A%7B%22release_date%22%3A%2208%2F24%2F2006%22%2C%22vendor%22%3A%22Xen%22%2C%22version%22%3A%224.2.amazon%22%7D%2C%22chassis%22%3A%7B%22type%22%3A%22Other%22%7D%2C%22manufacturer%22%3A%22Xen%22%2C%22product%22%3A%7B%22name%22%3A%22HVM%20domU%22%2C%22serial_number%22%3A%22ec2db5b3-d983-2a97-7457-fc8f699d88c6%22%2C%22uuid%22%3A%22B3B52DEC-83D9-972A-7457-FC8F699D88C6%22%7D%7D%2C%22domain%22%3A%22us-west-2.compute.amazonaws.com%22%2C%22ec2_metadata%22%3A%7B%22ami-id%22%3A%22ami-1b17242b%22%2C%22ami-launch-index%22%3A%220%22%2C%22ami-manifest-path%22%3A%22%28unknown%29%22%2C%22block-device-mapping%22%3A%7B%22ami%22%3A%22%2Fdev%2Fsda1%22%2C%22root%22%3A%22%2Fdev%2Fsda1%22%7D%2C%22hostname%22%3A%22ip-172-31-44-64.us-west-2.compute.internal%22%2C%22instance-action%22%3A%22none%22%2C%22instance-id%22%3A%22i-0c42deb6545e70775%22%2C%22instance-type%22%3A%22c4.xlarge%22%2C%22local-hostname%22%3A%22ip-172-31-44-64.us-west-2.compute.internal%22%2C%22local-ipv4%22%3A%22172.31.44.64%22%2C%22mac%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22metrics%22%3A%7B%22vhostmd%22%3A%22%3C%3Fxml%20version%3D%5C%221.0%5C%22%20encoding%3D%5C%22UTF-8%5C%22%3F%3E%22%7D%2C%22network%22%3A%7B%22interfaces%22%3A%7B%22macs%22%3A%7B%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%3A%7B%22device-number%22%3A%220%22%2C%22interface-id%22%3A%22eni-22bd1307%22%2C%22ipv4-associations%22%3A%7B%2254.218.223.235%22%3A%22172.31.44.64%22%7D%2C%22local-hostname%22%3A%22ip-172-31-44-64.us-west-2.compute.internal%22%2C%22local-ipv4s%22%3A%22172.31.44.64%22%2C%22mac%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22owner-id%22%3A%22028918822489%22%2C%22public-hostname%22%3A%22${node}%22%2C%22public-ipv4s%22%3A%2254.218.223.235%22%2C%22security-group-ids%22%3A%22sg-41b94725%5Cnsg-e3460586%22%2C%22security-groups%22%3A%22beaker-ping%5CnBeaker-3425116409%22%2C%22subnet-id%22%3A%22subnet-4860a82d%22%2C%22subnet-ipv4-cidr-block%22%3A%22172.31.32.0%2F20%22%2C%22vpc-id%22%3A%22vpc-bb9866de%22%2C%22vpc-ipv4-cidr-block%22%3A%22172.31.0.0%2F16%22%2C%22vpc-ipv4-cidr-blocks%22%3A%22172.31.0.0%2F16%22%7D%7D%7D%7D%2C%22placement%22%3A%7B%22availability-zone%22%3A%22us-west-2b%22%7D%2C%22product-codes%22%3A%22aw0evgkw8e5c1q413zgy5pjce%22%2C%22profile%22%3A%22default-hvm%22%2C%22public-hostname%22%3A%22${node}%22%2C%22public-ipv4%22%3A%2254.218.223.235%22%2C%22public-keys%22%3A%7B%220%22%3A%7B%22openssh-key%22%3A%22ssh-rsa%20AAAAB3NzaC1yc2EAAAADAQABAAACAQDAO2ao8gis26WKiJa%2FlQDvNz0S%2B7kOxNczo8ZZgFk0r7u5%2BNL5jtP5q3tswNcd70gcjZM01cPKL%2FUViycKLWb14qi8wV4J4%2FBnd2oxNnhy%2FlzgmpLvwdU3d1qf0bSIjc1uaT20WIzbF4W7M6TRkIxbQSyuqv7GGUy%2BLm%2BFpxor6mQDpbLx90txS7fWCljF9zNpY%2BMDb1gayEAUPNN43XgTVJ%2BeD4kjxzF1TL6t5aqEQp46V6xLgjroAI5EhE1qu0i9v6VLKI1CVGljN3nqw%2FEF9wykqD41vk3uzsGU4xHTQmNvAxrc1M9BjzUD9g8WVCJAmV8eEaR8K0uMVdtyzOxBRv6qF6LQ%2BFlAh5SwCJ8tJ248BriOakV6PxbvqWxRb3Y%2FJMmAnWzmEaOZ5TYLORKUEULmz3Y6Qi6B3cRF6C%2FWVvi%2BUmmKVdKhtYxyrMwW%2FSV1jYJ5Cg4Eec8Yqg%2Bs7lD%2FKtFsjErddZ%2FoObNtINq23LaBF%2BAy%2FIL%2FpjieEJ3KI6ZJwD6qR3AxoZ8G43QgtLZDbGz8fvZcqS3VvN4bWZoepEcFY%2BfHrfTcFwEbetQOx06e5fElbvjPIkDiNm%2FdKo6uuuhbPIgbX9EnOicd4nqh4TZglgc13rleWEsgL6p9%2FUhw1ARDCiYppqVmWUl6mP1%2FpsQ3TKKdMwvs%2FonFa6DMJw%3D%3D%20Beaker-drosser-drosser-7671751308-2018-01-31_08_53_18_177698294%22%7D%7D%2C%22reservation-id%22%3A%22r-0d0e4298d92ab4fe2%22%2C%22security-groups%22%3A%22beaker-ping%5CnBeaker-3425116409%22%2C%22services%22%3A%7B%22domain%22%3A%22amazonaws.com%22%2C%22partition%22%3A%22aws%22%7D%7D%2C%22facterversion%22%3A%223.9.1%22%2C%22fake_domain%22%3A%22pgtomcat.mycompany.org%22%2C%22filesystems%22%3A%22iso9660%2Cxfs%22%2C%22fqdn%22%3A%22${node}%22%2C%22function%22%3A%22app%22%2C%22gid%22%3A%22root%22%2C%22gitlab_systemd%22%3Atrue%2C%22group%22%3A%22pgtomcat%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22ec2-54-218-223-235%22%2C%22hypervisors%22%3A%7B%22xen%22%3A%7B%22context%22%3A%22hvm%22%2C%22privileged%22%3Afalse%7D%7D%2C%22id%22%3A%22root%22%2C%22identity%22%3A%7B%22gid%22%3A0%2C%22group%22%3A%22root%22%2C%22privileged%22%3Atrue%2C%22uid%22%3A0%2C%22user%22%3A%22root%22%7D%2C%22interfaces%22%3A%22eth0%2Clo%22%2C%22ip6tables_version%22%3A%221.4.21%22%2C%22ipaddress%22%3A%22172.31.44.64%22%2C%22ipaddress6%22%3A%22fe80%3A%3A42%3Ab4ff%3Afeae%3Abac6%22%2C%22ipaddress6_eth0%22%3A%22fe80%3A%3A42%3Ab4ff%3Afeae%3Abac6%22%2C%22ipaddress6_lo%22%3A%22%3A%3A1%22%2C%22ipaddress_eth0%22%3A%22172.31.44.64%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22iptables_version%22%3A%221.4.21%22%2C%22is_pe%22%3Afalse%2C%22is_virtual%22%3Atrue%2C%22java_default_home%22%3A%22%2Fusr%2Flib%2Fjvm%2Fjava-1.8.0-openjdk-1.8.0.161-0.b14.el7_4.x86_64%22%2C%22java_libjvm_path%22%3A%22%2Fusr%2Flib%2Fjvm%2Fjava-1.8.0-openjdk-1.8.0.161-0.b14.el7_4.x86_64%2Fjre%2Flib%2Famd64%2Fserver%22%2C%22java_major_version%22%3A%228%22%2C%22java_patch_level%22%3A%22161%22%2C%22java_version%22%3A%221.8.0_161%22%2C%22jenkins_plugins%22%3A%22%22%2C%22kernel%22%3A%22Linux%22%2C%22kernelmajversion%22%3A%223.10%22%2C%22kernelrelease%22%3A%223.10.0-123.8.1.el7.x86_64%22%2C%22kernelversion%22%3A%223.10.0%22%2C%22load_averages%22%3A%7B%2215m%22%3A0.06%2C%221m%22%3A0.25%2C%225m%22%3A0.09%7D%2C%22lsbdistcodename%22%3A%22Core%22%2C%22lsbdistdescription%22%3A%22CentOS%20Linux%20release%207.0.1406%20%28Core%29%22%2C%22lsbdistid%22%3A%22CentOS%22%2C%22lsbdistrelease%22%3A%227.0.1406%22%2C%22lsbmajdistrelease%22%3A%227%22%2C%22lsbminordistrelease%22%3A%220%22%2C%22lsbrelease%22%3A%22%3Acore-4.1-amd64%3Acore-4.1-noarch%3Acxx-4.1-amd64%3Acxx-4.1-noarch%3Adesktop-4.1-amd64%3Adesktop-4.1-noarch%3Alanguages-4.1-amd64%3Alanguages-4.1-noarch%3Aprinting-4.1-amd64%3Aprinting-4.1-noarch%22%2C%22macaddress%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22macaddress_eth0%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22manufacturer%22%3A%22Xen%22%2C%22memory%22%3A%7B%22system%22%3A%7B%22available%22%3A%225.72%20GiB%22%2C%22available_bytes%22%3A6138793984%2C%22capacity%22%3A%2217.11%25%22%2C%22total%22%3A%226.90%20GiB%22%2C%22total_bytes%22%3A7406047232%2C%22used%22%3A%221.18%20GiB%22%2C%22used_bytes%22%3A1267253248%7D%7D%2C%22memoryfree%22%3A%225.72%20GiB%22%2C%22memoryfree_mb%22%3A5854.41015625%2C%22memorysize%22%3A%226.90%20GiB%22%2C%22memorysize_mb%22%3A7062.95703125%2C%22mountpoints%22%3A%7B%22%2F%22%3A%7B%22available%22%3A%2247.39%20GiB%22%2C%22available_bytes%22%3A50883530752%2C%22capacity%22%3A%225.20%25%22%2C%22device%22%3A%22%2Fdev%2Fxvda1%22%2C%22filesystem%22%3A%22xfs%22%2C%22options%22%3A%5B%22rw%22%2C%22seclabel%22%2C%22relatime%22%2C%22attr2%22%2C%22inode64%22%2C%22noquota%22%5D%2C%22size%22%3A%2249.99%20GiB%22%2C%22size_bytes%22%3A53674864640%2C%22used%22%3A%222.60%20GiB%22%2C%22used_bytes%22%3A2791333888%7D%2C%22%2Fdev%2Fshm%22%3A%7B%22available%22%3A%223.45%20GiB%22%2C%22available_bytes%22%3A3703021568%2C%22capacity%22%3A%220%25%22%2C%22device%22%3A%22tmpfs%22%2C%22filesystem%22%3A%22tmpfs%22%2C%22options%22%3A%5B%22rw%22%2C%22seclabel%22%2C%22nosuid%22%2C%22nodev%22%5D%2C%22size%22%3A%223.45%20GiB%22%2C%22size_bytes%22%3A3703021568%2C%22used%22%3A%220%20bytes%22%2C%22used_bytes%22%3A0%7D%2C%22%2Frun%22%3A%7B%22available%22%3A%223.42%20GiB%22%2C%22available_bytes%22%3A3677536256%2C%22capacity%22%3A%220.69%25%22%2C%22device%22%3A%22tmpfs%22%2C%22filesystem%22%3A%22tmpfs%22%2C%22options%22%3A%5B%22rw%22%2C%22seclabel%22%2C%22nosuid%22%2C%22nodev%22%2C%22mode%3D755%22%5D%2C%22size%22%3A%223.45%20GiB%22%2C%22size_bytes%22%3A3703021568%2C%22used%22%3A%2224.30%20MiB%22%2C%22used_bytes%22%3A25485312%7D%2C%22%2Frun%2Fuser%2F0%22%3A%7B%22available%22%3A%22706.30%20MiB%22%2C%22available_bytes%22%3A740605952%2C%22capacity%22%3A%220%25%22%2C%22device%22%3A%22tmpfs%22%2C%22filesystem%22%3A%22tmpfs%22%2C%22options%22%3A%5B%22rw%22%2C%22seclabel%22%2C%22nosuid%22%2C%22nodev%22%2C%22relatime%22%2C%22size%3D723248k%22%2C%22mode%3D700%22%5D%2C%22size%22%3A%22706.30%20MiB%22%2C%22size_bytes%22%3A740605952%2C%22used%22%3A%220%20bytes%22%2C%22used_bytes%22%3A0%7D%2C%22%2Fsys%2Ffs%2Fcgroup%22%3A%7B%22available%22%3A%223.45%20GiB%22%2C%22available_bytes%22%3A3703021568%2C%22capacity%22%3A%220%25%22%2C%22device%22%3A%22tmpfs%22%2C%22filesystem%22%3A%22tmpfs%22%2C%22options%22%3A%5B%22ro%22%2C%22seclabel%22%2C%22nosuid%22%2C%22nodev%22%2C%22noexec%22%2C%22mode%3D755%22%5D%2C%22size%22%3A%223.45%20GiB%22%2C%22size_bytes%22%3A3703021568%2C%22used%22%3A%220%20bytes%22%2C%22used_bytes%22%3A0%7D%7D%2C%22mtu_eth0%22%3A9001%2C%22mtu_lo%22%3A65536%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_eth0%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_lo%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22netmask_eth0%22%3A%22255.255.240.0%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22network%22%3A%22172.31.32.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22network6_eth0%22%3A%22fe80%3A%3A%22%2C%22network6_lo%22%3A%22%3A%3A1%22%2C%22network_eth0%22%3A%22172.31.32.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22networking%22%3A%7B%22dhcp%22%3A%22172.31.32.1%22%2C%22domain%22%3A%22us-west-2.compute.amazonaws.com%22%2C%22fqdn%22%3A%22${node}%22%2C%22hostname%22%3A%22ec2-54-218-223-235%22%2C%22interfaces%22%3A%7B%22eth0%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22172.31.44.64%22%2C%22netmask%22%3A%22255.255.240.0%22%2C%22network%22%3A%22172.31.32.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22fe80%3A%3A42%3Ab4ff%3Afeae%3Abac6%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22fe80%3A%3A%22%7D%5D%2C%22dhcp%22%3A%22172.31.32.1%22%2C%22ip%22%3A%22172.31.44.64%22%2C%22ip6%22%3A%22fe80%3A%3A42%3Ab4ff%3Afeae%3Abac6%22%2C%22mac%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22mtu%22%3A9001%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22172.31.32.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%7D%2C%22lo%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22127.0.0.1%22%2C%22netmask%22%3A%22255.0.0.0%22%2C%22network%22%3A%22127.0.0.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22%3A%3A1%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22%3A%3A1%22%7D%5D%2C%22ip%22%3A%22127.0.0.1%22%2C%22ip6%22%3A%22%3A%3A1%22%2C%22mtu%22%3A65536%2C%22netmask%22%3A%22255.0.0.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22127.0.0.0%22%2C%22network6%22%3A%22%3A%3A1%22%7D%7D%2C%22ip%22%3A%22172.31.44.64%22%2C%22ip6%22%3A%22fe80%3A%3A42%3Ab4ff%3Afeae%3Abac6%22%2C%22mac%22%3A%2202%3A42%3Ab4%3Aae%3Aba%3Ac6%22%2C%22mtu%22%3A9001%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22172.31.32.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22primary%22%3A%22eth0%22%7D%2C%22operatingsystem%22%3A%22CentOS%22%2C%22operatingsystemmajrelease%22%3A%227%22%2C%22operatingsystemrelease%22%3A%227.0.1406%22%2C%22os%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22distro%22%3A%7B%22codename%22%3A%22Core%22%2C%22description%22%3A%22CentOS%20Linux%20release%207.0.1406%20%28Core%29%22%2C%22id%22%3A%22CentOS%22%2C%22release%22%3A%7B%22full%22%3A%227.0.1406%22%2C%22major%22%3A%227%22%2C%22minor%22%3A%220%22%7D%2C%22specification%22%3A%22%3Acore-4.1-amd64%3Acore-4.1-noarch%3Acxx-4.1-amd64%3Acxx-4.1-noarch%3Adesktop-4.1-amd64%3Adesktop-4.1-noarch%3Alanguages-4.1-amd64%3Alanguages-4.1-noarch%3Aprinting-4.1-amd64%3Aprinting-4.1-noarch%22%7D%2C%22family%22%3A%22RedHat%22%2C%22hardware%22%3A%22x86_64%22%2C%22name%22%3A%22CentOS%22%2C%22release%22%3A%7B%22full%22%3A%227.0.1406%22%2C%22major%22%3A%227%22%2C%22minor%22%3A%220%22%7D%2C%22selinux%22%3A%7B%22config_mode%22%3A%22enforcing%22%2C%22config_policy%22%3A%22targeted%22%2C%22current_mode%22%3A%22permissive%22%2C%22enabled%22%3Atrue%2C%22enforced%22%3Afalse%2C%22policy_version%22%3A%2228%22%7D%7D%2C%22osfamily%22%3A%22RedHat%22%2C%22package_provider%22%3A%22yum%22%2C%22partitions%22%3A%7B%22%2Fdev%2Fxvda1%22%3A%7B%22filesystem%22%3A%22xfs%22%2C%22mount%22%3A%22%2F%22%2C%22size%22%3A%2250.00%20GiB%22%2C%22size_bytes%22%3A53685353984%2C%22uuid%22%3A%220f790447-ebef-4ca0-b229-d0aa1985d57f%22%7D%7D%2C%22path%22%3A%22%2Fusr%2Flocal%2Frvm%2Fgems%2Fruby-2.2.5%2Fbin%3A%2Fusr%2Flocal%2Frvm%2Fgems%2Fruby-2.2.5%40global%2Fbin%3A%2Fusr%2Flocal%2Frvm%2Frubies%2Fruby-2.2.5%2Fbin%3A%2Fusr%2Flib64%2Fqt-3.3%2Fbin%3APATH%3A%2Fopt%2Fpuppetlabs%2Fbin%3A%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Fusr%2Flocal%2Frvm%2Fbin%3A%2Froot%2Fbin%3A%2Fsbin%22%2C%22pe_concat_basedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%2Fpe_concat%22%2C%22pe_razor_server_version%22%3A%22package%20pe-razor-server%20is%20not%20installed%22%2C%22physicalprocessorcount%22%3A1%2C%22platform_symlink_writable%22%3Atrue%2C%22platform_tag%22%3A%22el-7-x86_64%22%2C%22processor0%22%3A%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22processor1%22%3A%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22processor2%22%3A%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22processor3%22%3A%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22processorcount%22%3A4%2C%22processors%22%3A%7B%22count%22%3A4%2C%22isa%22%3A%22x86_64%22%2C%22models%22%3A%5B%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%2C%22Intel%28R%29%20Xeon%28R%29%20CPU%20E5-2666%20v3%20%40%202.90GHz%22%5D%2C%22physicalcount%22%3A1%7D%2C%22productname%22%3A%22HVM%20domU%22%2C%22puppet_files_dir_present%22%3Afalse%2C%22puppet_inventory_metadata%22%3A%7B%22packages%22%3A%7B%22collection_enabled%22%3Afalse%2C%22last_collection_time%22%3A%220.0s%22%7D%7D%2C%22puppet_vardir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%22%2C%22puppetversion%22%3A%225.3.0%22%2C%22root_home%22%3A%22%2Froot%22%2C%22rsyslog_version%22%3A%227.4.7%22%2C%22ruby%22%3A%7B%22platform%22%3A%22x86_64-linux%22%2C%22sitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.4.0%22%2C%22version%22%3A%222.4.1%22%7D%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.4.0%22%2C%22rubyversion%22%3A%222.4.1%22%2C%22selinux%22%3Atrue%2C%22selinux_config_mode%22%3A%22enforcing%22%2C%22selinux_config_policy%22%3A%22targeted%22%2C%22selinux_current_mode%22%3A%22permissive%22%2C%22selinux_enforced%22%3Afalse%2C%22selinux_policyversion%22%3A%2228%22%2C%22serialnumber%22%3A%22ec2db5b3-d983-2a97-7457-fc8f699d88c6%22%2C%22service_provider%22%3A%22systemd%22%2C%22ssh%22%3A%7B%22ecdsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP%203%201%2039c008a1084fd645af21ca73831a515c86beb21c%22%2C%22sha256%22%3A%22SSHFP%203%202%20c4b630841b97333597a517ada8419a4ae9f8ddd7ad33c4043663a310f3a5de7f%22%7D%2C%22key%22%3A%22AAAAE2VjZHNhLXNoYTItbmlzdHAyNTYAAAAIbmlzdHAyNTYAAABBBMD2xqC7aA%2FWcq691vLjx0UWjeWxG2CCVbBZVrI6aw4dvf0hapB5RalbnXIjGz12oIPqgiezRC%2FxjkHkvQvOEw4%3D%22%7D%2C%22ed25519%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP%204%201%20d80a0cc0b5e4821751247088e8c08898dbc3e2a1%22%2C%22sha256%22%3A%22SSHFP%204%202%20d058100ca2720e9bf081990ae74651a38d3081beab6e535b200f0aa4a2c74304%22%7D%2C%22key%22%3A%22AAAAC3NzaC1lZDI1NTE5AAAAINFE8YdfdAaOLgPm65mYtgvrDPqCJDhrXpdqsY7V%2BneU%22%7D%2C%22rsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP%201%201%2077d992496e8f1bce51a6fe5159d6ba27056f6c9d%22%2C%22sha256%22%3A%22SSHFP%201%202%20eba50e1a929cd452025c50f5bd24bef45875605386b7da5fa80fda37339c853d%22%7D%2C%22key%22%3A%22AAAAB3NzaC1yc2EAAAADAQABAAABAQDEYrFRJiUVaLG%2BQgsU2GcxjPbkLwN7HBakRp6%2FRYF9M4uFhMme%2FBqRTk8UJf09kMiLkxxmssEwH%2FioDjIi9QLrqGXT%2FsbS%2BfzN0Yc0JWsftr0mgSqp%2BAWiG452mg2bV7snTEPkz%2Fk1d0h01T3M0xtTQkC0XFi%2BemDwcr9J%2BgGCmrLD%2BogsJqlekk0wC1AHl7qEYIWe%2FPzF%2Bi50xBSHF3KgQY7%2BYtsp7lEr6xnE%2B38TG7z7g4hW8DPbCIpvPeauPkTSFtUHRIgWYg%2Fxk94Wo1yIGRB%2FifBFKZ3CwJ9dzGJr81X1YoO6vUlI%2FPiP1RnKg2E9BD0pVYzxq%2FAGlr0k9ZVH%22%7D%7D%2C%22sshecdsakey%22%3A%22AAAAE2VjZHNhLXNoYTItbmlzdHAyNTYAAAAIbmlzdHAyNTYAAABBBMD2xqC7aA%2FWcq691vLjx0UWjeWxG2CCVbBZVrI6aw4dvf0hapB5RalbnXIjGz12oIPqgiezRC%2FxjkHkvQvOEw4%3D%22%2C%22sshed25519key%22%3A%22AAAAC3NzaC1lZDI1NTE5AAAAINFE8YdfdAaOLgPm65mYtgvrDPqCJDhrXpdqsY7V%2BneU%22%2C%22sshfp_ecdsa%22%3A%22SSHFP%203%201%2039c008a1084fd645af21ca73831a515c86beb21c%5CnSSHFP%203%202%20c4b630841b97333597a517ada8419a4ae9f8ddd7ad33c4043663a310f3a5de7f%22%2C%22sshfp_ed25519%22%3A%22SSHFP%204%201%20d80a0cc0b5e4821751247088e8c08898dbc3e2a1%5CnSSHFP%204%202%20d058100ca2720e9bf081990ae74651a38d3081beab6e535b200f0aa4a2c74304%22%2C%22sshfp_rsa%22%3A%22SSHFP%201%201%2077d992496e8f1bce51a6fe5159d6ba27056f6c9d%5CnSSHFP%201%202%20eba50e1a929cd452025c50f5bd24bef45875605386b7da5fa80fda37339c853d%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAADAQABAAABAQDEYrFRJiUVaLG%2BQgsU2GcxjPbkLwN7HBakRp6%2FRYF9M4uFhMme%2FBqRTk8UJf09kMiLkxxmssEwH%2FioDjIi9QLrqGXT%2FsbS%2BfzN0Yc0JWsftr0mgSqp%2BAWiG452mg2bV7snTEPkz%2Fk1d0h01T3M0xtTQkC0XFi%2BemDwcr9J%2BgGCmrLD%2BogsJqlekk0wC1AHl7qEYIWe%2FPzF%2Bi50xBSHF3KgQY7%2BYtsp7lEr6xnE%2B38TG7z7g4hW8DPbCIpvPeauPkTSFtUHRIgWYg%2Fxk94Wo1yIGRB%2FifBFKZ3CwJ9dzGJr81X1YoO6vUlI%2FPiP1RnKg2E9BD0pVYzxq%2FAGlr0k9ZVH%22%2C%22stage%22%3A%22prod%22%2C%22staging_http_get%22%3A%22curl%22%2C%22system_uptime%22%3A%7B%22days%22%3A0%2C%22hours%22%3A4%2C%22seconds%22%3A17947%2C%22uptime%22%3A%224%3A59%20hours%22%7D%2C%22timezone%22%3A%22UTC%22%2C%22uptime%22%3A%224%3A59%20hours%22%2C%22uptime_days%22%3A0%2C%22uptime_hours%22%3A4%2C%22uptime_seconds%22%3A17947%2C%22uuid%22%3A%22B3B52DEC-83D9-972A-7457-FC8F699D88C6%22%2C%22virtual%22%3A%22xenhvm%22%2C%22whereami%22%3A%22portland%22%2C%22clientcert%22%3A%22${node}%22%2C%22clientversion%22%3A%225.3.0%22%2C%22clientnoop%22%3Afalse%7D%2C%22timestamp%22%3A%222018-01-31T21%3A52%3A51.914018910%2B00%3A00%22%2C%22expiration%22%3A%222125-01-31T22%3A22%3A51.914345658%2B00%3A00%22%7D")
			.formParam("transaction_uuid", "83e3ab94-028c-4331-9708-7a1fef8ef242")
			.formParam("static_catalog", "true")
			.formParam("checksum_type", "md5.sha256")
			.formParam("fail_on_404", "true"))
		.pause(2)
		.exec(http("filemeta mco plugins")
			.get("/puppet/v3/file_metadatas/modules/puppet_enterprise/mcollective/plugins?environment=production&links=manage&recurse=true&source_permissions=ignore&checksum_type=md5")
			.headers(headers_0))
		.pause(4)
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

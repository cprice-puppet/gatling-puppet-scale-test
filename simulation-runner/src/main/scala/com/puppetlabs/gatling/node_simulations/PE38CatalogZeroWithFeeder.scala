package com.puppetlabs.gatling.node_simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import io.gatling.jdbc.Predef._
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

class PE38_CatalogZeroWithFeeder extends SimulationWithScenario {

//	val httpProtocol = http
//		.baseURL("https://jb-centos7:8140")
//		.acceptHeader("pson, b64_zlib_yaml, yaml, raw")
//		.acceptEncodingHeader("identity")
//		.contentTypeHeader("application/x-www-form-urlencoded")
//		.userAgentHeader("Ruby")

	val headers_3 = Map("Accept" -> "pson, b64_zlib_yaml, yaml, dot, raw")

	val headers_107 = Map(
		"Accept" -> "pson, yaml",
		"Content-Type" -> "text/pson",
    "Connection" -> "close")

  val nodeNames = csv("nodes.250.csv").circular

//    val uri1 = "https://jb-centos7:8140/production"

  val	reportBody = ELFileBody("PE38CatalogZeroWithFeeder_0107_request.txt")

	val chain_0 = feed(nodeNames)
      .exec(http("node")
			.get("/production/node/${node}?transaction_uuid=2eabf4c0-acf8-466f-a0e4-d75519be6afc&fail_on_404=true"))
		.exec(http("filemeta pluginfacts")
			.get("/production/file_metadatas/pluginfacts?links=manage&recurse=true&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5"))
		.pause(282 milliseconds)
		.exec(http("filemeta plugins")
			.get("/production/file_metadatas/plugins?links=manage&recurse=true&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5"))
		.pause(515 milliseconds)
		.exec(http("catalog")
			.post("/production/catalog/${node}")
			.headers(headers_3)
			.formParam("facts_format", "pson")
			.formParam("facts", "%7B%22name%22%3A%22${node}%22%2C%22values%22%3A%7B%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAADAQABAAABAQDKXfFUhyLWcTWGn2qpNMykPF85PAA22QxdOrG%2BFlUGTfkQnzqx%2FAeAQA1WIqZ%2FHNgDtUx97Zia15Zbj%2FdWpGRu0h%2BSQvD36fNiMgOHp11QNSlyoXFsC7dBBJKl7vuVRaR6aaUCTlAIxspcv1NjMTlhLZn3ZSIS7yvhEwy5kB%2BJcPBiLVioebFxYJ6oOAau%2FjVglGLlnPzADg6coEi65xxaybu1WW9jCqlqNW394fWfkQwuyo7FESARy%2BtW1AfqeCeUvId6IvF3uB9Msh0%2BRU1URfWyo6W9Cg5Cd%2BSWyfvivLvwtyZRJrljfu6Q2loH9PgqO67q68TVcvzH7HRbdq47%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+f674090612cc168af7c5b6f80bc560cd5ef1f410%5CnSSHFP+1+2+4cb4bd147a354a921794d28bd92fa07ebe3dd3295397829a3dc7f205300bf0f1%22%2C%22sshecdsakey%22%3A%22AAAAE2VjZHNhLXNoYTItbmlzdHAyNTYAAAAIbmlzdHAyNTYAAABBBGtbZMp7or19c0otdzVdzH9FL590Xqjx%2B1Cyry2P7ZQv1yEZZsPDv9hbTRUq3giiABgfEr0tPKkqqP6Qt0eQzkE%3D%22%2C%22sshfp_ecdsa%22%3A%22SSHFP+3+1+56e40182974e89869598a4ecc1295df2683447ec%5CnSSHFP+3+2+f1c888ae5e2d801f5be37bcb8c499c11d2c4e02673dff265ac2a871bcdd3399f%22%2C%22architecture%22%3A%22x86_64%22%2C%22system_uptime%22%3A%7B%22seconds%22%3A4755%2C%22hours%22%3A1%2C%22days%22%3A0%2C%22uptime%22%3A%221%3A19+hours%22%7D%2C%22augeasversion%22%3A%221.3.0%22%2C%22timezone%22%3A%22PDT%22%2C%22kernel%22%3A%22Linux%22%2C%22blockdevice_fd0_size%22%3A4096%2C%22blockdevice_sda_size%22%3A21474836480%2C%22blockdevice_sda_vendor%22%3A%22VMware%2C%22%2C%22blockdevice_sda_model%22%3A%22VMware+Virtual+S%22%2C%22blockdevice_sr0_size%22%3A1073741312%2C%22blockdevice_sr0_vendor%22%3A%22NECVMWar%22%2C%22blockdevice_sr0_model%22%3A%22VMware+IDE+CDR10%22%2C%22blockdevices%22%3A%22fd0%2Csda%2Csr0%22%2C%22dhcp_servers%22%3A%7B%22system%22%3A%22192.168.7.254%22%2C%22eno16777736%22%3A%22192.168.7.254%22%7D%2C%22domain%22%3A%22localdomain%22%2C%22virtual%22%3A%22vmware%22%2C%22is_virtual%22%3Atrue%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22operatingsystem%22%3A%22CentOS%22%2C%22os%22%3A%7B%22name%22%3A%22CentOS%22%2C%22family%22%3A%22RedHat%22%2C%22release%22%3A%7B%22major%22%3A%227%22%2C%22minor%22%3A%220%22%2C%22full%22%3A%227.0.1406%22%7D%7D%2C%22facterversion%22%3A%222.4.2%22%2C%22filesystems%22%3A%22xfs%22%2C%22fqdn%22%3A%22${node}%22%2C%22gid%22%3A%22root%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hostname%22%3A%22jb-centos7-agent%22%2C%22id%22%3A%22root%22%2C%22interfaces%22%3A%22%22%2C%22ipaddress%22%3A%22%3A%3A1%22%2C%22kernelmajversion%22%3A%223.10%22%2C%22kernelrelease%22%3A%223.10.0-123.el7.x86_64%22%2C%22kernelversion%22%3A%223.10.0%22%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F1.9.1%22%2C%22boardmanufacturer%22%3A%22Intel+Corporation%22%2C%22boardproductname%22%3A%22440BX+Desktop+Reference+Platform%22%2C%22boardserialnumber%22%3A%22None%22%2C%22bios_vendor%22%3A%22Phoenix+Technologies+LTD%22%2C%22bios_version%22%3A%226.00%22%2C%22bios_release_date%22%3A%2207%2F31%2F2013%22%2C%22manufacturer%22%3A%22VMware%2C+Inc.%22%2C%22productname%22%3A%22VMware+Virtual+Platform%22%2C%22serialnumber%22%3A%22VMware-56+4d+c2+99+cc+0e+04+3d-87+1a+b1+84+6c+24+d3+5b%22%2C%22uuid%22%3A%22564DC299-CC0E-043D-871A-B1846C24D35B%22%2C%22type%22%3A%22Other%22%2C%22memorysize%22%3A%222.78+GB%22%2C%22memoryfree%22%3A%222.42+GB%22%2C%22swapsize%22%3A%222.00+GB%22%2C%22swapfree%22%3A%222.00+GB%22%2C%22swapsize_mb%22%3A%222048.00%22%2C%22swapfree_mb%22%3A%222048.00%22%2C%22memorysize_mb%22%3A%222842.17%22%2C%22memoryfree_mb%22%3A%222479.46%22%2C%22operatingsystemmajrelease%22%3A%227%22%2C%22rubyversion%22%3A%221.9.3%22%2C%22operatingsystemrelease%22%3A%227.0.1406%22%2C%22osfamily%22%3A%22RedHat%22%2C%22partitions%22%3A%7B%22sda1%22%3A%7B%22uuid%22%3A%2229c8f17a-3a5c-4c9f-b64b-6bff72c98485%22%2C%22size%22%3A%221024000%22%2C%22mount%22%3A%22%2Fboot%22%2C%22filesystem%22%3A%22xfs%22%7D%2C%22sda2%22%3A%7B%22size%22%3A%2240916992%22%2C%22filesystem%22%3A%22LVM2_member%22%7D%7D%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%3A%2Fsbin%22%2C%22selinux%22%3Atrue%2C%22selinux_enforced%22%3Atrue%2C%22selinux_policyversion%22%3A%2228%22%2C%22selinux_current_mode%22%3A%22enforcing%22%2C%22selinux_config_mode%22%3A%22enforcing%22%2C%22selinux_config_policy%22%3A%22targeted%22%2C%22physicalprocessorcount%22%3A1%2C%22processors%22%3A%7B%22models%22%3A%5B%22Intel%28R%29+Core%28TM%29+i7-4850HQ+CPU+%40+2.30GHz%22%5D%2C%22count%22%3A1%2C%22physicalcount%22%3A1%7D%2C%22processor0%22%3A%22Intel%28R%29+Core%28TM%29+i7-4850HQ+CPU+%40+2.30GHz%22%2C%22processorcount%22%3A1%2C%22ps%22%3A%22ps+-ef%22%2C%22puppetversion%22%3A%223.7.5+%28Puppet+Enterprise+3.8.0%29%22%2C%22uniqueid%22%3A%22007f0100%22%2C%22uptime%22%3A%221%3A19+hours%22%2C%22uptime_days%22%3A0%2C%22uptime_hours%22%3A1%2C%22uptime_seconds%22%3A4755%2C%22custom_auth_conf%22%3A%22false%22%2C%22pe_build%22%3A%223.8.0%22%2C%22pe_concat_basedir%22%3A%22%2Fvar%2Fopt%2Flib%2Fpe-puppet%2Fpe_concat%22%2C%22pe_version%22%3A%223.8.0%22%2C%22is_pe%22%3Atrue%2C%22pe_major_version%22%3A%223%22%2C%22pe_minor_version%22%3A%228%22%2C%22pe_patch_version%22%3A%220%22%2C%22platform_symlink_writable%22%3Atrue%2C%22platform_tag%22%3A%22el-7-x86_64%22%2C%22staging_http_get%22%3A%22curl%22%2C%22clientcert%22%3A%22${node}%22%2C%22clientversion%22%3A%223.7.5+%28Puppet+Enterprise+3.8.0%29%22%2C%22clientnoop%22%3Afalse%7D%2C%22timestamp%22%3A%222015-04-08+11%3A13%3A19+-0700%22%2C%22expiration%22%3A%222025-04-08T12%3A43%3A19.304641797-07%3A00%22%7D")
			.formParam("transaction_uuid", "2eabf4c0-acf8-466f-a0e4-d75519be6afc")
			.formParam("fail_on_404", "true"))
		.pause(2)
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero1/catalog_zero1_impl24.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero1/catalog_zero1_impl71.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero1/catalog_zero1_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero2/catalog_zero2_impl51.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero3/catalog_zero3_impl23.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero3/catalog_zero3_impl32.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero3/catalog_zero3_impl74.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero4/catalog_zero4_impl13.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero5/catalog_zero5_impl22.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero5/catalog_zero5_impl42.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero5/catalog_zero5_impl43.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero5/catalog_zero5_impl52.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero6/catalog_zero6_impl32.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero6/catalog_zero6_impl54.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero7/catalog_zero7_impl32.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero7/catalog_zero7_impl34.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero7/catalog_zero7_impl82.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero7/catalog_zero7_impl85.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero7/catalog_zero7_impl87.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero8/catalog_zero8_impl11.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero8/catalog_zero8_impl13.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero8/catalog_zero8_impl33.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero8/catalog_zero8_impl81.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero9/catalog_zero9_impl63.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero9/catalog_zero9_impl72.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero9/catalog_zero9_impl84.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl13.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl22.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl41.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl71.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero10/catalog_zero10_impl85.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero11/catalog_zero11_impl11.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero11/catalog_zero11_impl21.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero11/catalog_zero11_impl24.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero11/catalog_zero11_impl62.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero11/catalog_zero11_impl84.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero12/catalog_zero12_impl33.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero12/catalog_zero12_impl82.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero12/catalog_zero12_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero12/catalog_zero12_impl87.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl51.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl54.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl74.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl84.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero13/catalog_zero13_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero14/catalog_zero14_impl72.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero15/catalog_zero15_impl62.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero15/catalog_zero15_impl72.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero15/catalog_zero15_impl73.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero16/catalog_zero16_impl33.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero16/catalog_zero16_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero16/catalog_zero16_impl85.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero17/catalog_zero17_impl13.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero17/catalog_zero17_impl87.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero18/catalog_zero18_impl14.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero18/catalog_zero18_impl23.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero18/catalog_zero18_impl24.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero18/catalog_zero18_impl32.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero18/catalog_zero18_impl84.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl12.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl31.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl53.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl54.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero19/catalog_zero19_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero20/catalog_zero20_impl24.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero20/catalog_zero20_impl42.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero20/catalog_zero20_impl71.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero20/catalog_zero20_impl73.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero21/catalog_zero21_impl32.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero21/catalog_zero21_impl41.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero21/catalog_zero21_impl82.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero21/catalog_zero21_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero21/catalog_zero21_impl87.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero22/catalog_zero22_impl34.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero22/catalog_zero22_impl52.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero23/catalog_zero23_impl21.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero23/catalog_zero23_impl62.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero24/catalog_zero24_impl52.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero24/catalog_zero24_impl83.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero25/catalog_zero25_impl13.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero25/catalog_zero25_impl22.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero25/catalog_zero25_impl24.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero25/catalog_zero25_impl51.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero26/catalog_zero26_impl54.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero26/catalog_zero26_impl64.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero26/catalog_zero26_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero27/catalog_zero27_impl33.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero27/catalog_zero27_impl41.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero27/catalog_zero27_impl63.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl13.txt?links=manage&source_permissions=use"))

 val chain_1 = exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl33.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl42.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl51.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl72.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero28/catalog_zero28_impl84.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero29/catalog_zero29_impl71.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero29/catalog_zero29_impl86.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero30/catalog_zero30_impl34.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta")
			.get("/production/file_metadata/modules/catalog_zero30/catalog_zero30_impl85.txt?links=manage&source_permissions=use"))
		.exec(http("filemeta mco plugins")
			.get("/production/file_metadatas/modules/puppet_enterprise/mcollective/plugins?links=manage&recurse=true&checksum_type=md5"))
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
			.put("/production/report/${node}")
			.headers(headers_107)
			.body(reportBody))
					
	val scn = scenario("PE38CatalogZeroWithFeeder").exec(
		chain_0, chain_1)

//	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}

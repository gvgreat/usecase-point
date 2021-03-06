<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="html" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<html>
			<font face="Verdana" size="1">
				<body>
					<hr />
					<h2>Unadjusted Use Case Weights</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>
								<font face="Verdana" size="2">
									Use Case Type
								</font>
							</th>
							<th>
								<font face="Verdana" size="2">
									Description
								</font>
							</th>
							<th>
								<font face="Verdana" size="2">
									Weight
								</font>
							</th>
							<th>
								<font face="Verdana" size="2">
									Number of Use Cases
								</font>
							</th>
							<th>
								<font face="Verdana" size="2">
									Result (weight*no. of use cases)
								</font>
							</th>
						</tr>
						<xsl:for-each
							select="unadjusted-usecase-weights/usecase-weight">
							<tr>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of select="type" />
									</font>
								</td>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of
											select="description" />
									</font>
								</td>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of select="weight" />
									</font>
								</td>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of
											select="num-cases" />
									</font>
								</td>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of select="result" />
									</font>
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td />
							<td />
							<td />
							<td>
								<font face="Verdana" size="2">
									Total UUCW
								</font>
							</td>
							<td>
								<font face="Verdana" size="2">
									<b>
										<xsl:value-of
											select="unadjusted-usecase-weights/total-UUCW" />
									</b>
								</font>
							</td>
						</tr>
					</table>
					<br />
					<hr />
					<h3>
						Total UUCW =
						<xsl:value-of
							select="unadjusted-usecase-weights/total-UUCW" />
					</h3>
				</body>
			</font>
		</html>
	</xsl:template>
</xsl:stylesheet>

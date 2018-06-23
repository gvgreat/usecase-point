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
					<h2>Technical Complexity Factors</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>
								<font face="Verdana" size="2">
									Factor
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
									Perceived Complexity
								</font>
							</th>
							<th>
								<font face="Verdana" size="2">
									Calculated Factor (weight*perceived
									complexity)
								</font>
							</th>
						</tr>
						<xsl:for-each
							select="technical-complexity-factors/technical-factor">
							<tr>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of select="factor" />
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
											select="complexity" />
									</font>
								</td>
								<td>
									<font face="Verdana" size="2">
										<xsl:value-of
											select="calculated-factor" />
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
									Total
								</font>
							</td>
							<td>
								<font face="Verdana" size="2">
									<b>
										<xsl:value-of
											select="technical-complexity-factors/total-value" />
									</b>
								</font>
							</td>
						</tr>
					</table>
					<br />
					<hr />
					<h3>
						TCF (0.6 + (0.01*Total Factor)) =
						<xsl:value-of
							select="technical-complexity-factors/calculated-tcf" />
					</h3>
				</body>
			</font>
		</html>
	</xsl:template>
</xsl:stylesheet>

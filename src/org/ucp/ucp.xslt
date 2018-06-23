<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="html" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<html>
			<font face="Verdana">
				<body>
					<!-- ################################## -->
					<!-- TECHNICAL COMPLEXITY FACTORS -->
					<!-- ################################## -->
					<h2>Technical Complexity Factors</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>Factor</th>
							<th>Description</th>
							<th>Weight</th>
							<th>Perceived Complexity</th>
							<th>
								Calculated Factor (weight*perceived
								complexity)
							</th>
						</tr>
						<xsl:for-each
							select="technical-complexity-factors/technical-factor">
							<tr>
								<td>
									<xsl:value-of select="factor" />
								</td>
								<td>
									<xsl:value-of select="description" />
								</td>
								<td>
									<xsl:value-of select="weight" />
								</td>
								<td>
									<xsl:value-of select="complexity" />
								</td>
								<td>
									<xsl:value-of
										select="calculated-factor" />
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td />
							<td />
							<td />
							<td>Total</td>
							<td>
								<b>
									<xsl:value-of
										select="technical-complexity-factors/total-value" />
								</b>
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

					<hr />
					<!-- ################################## -->
					<!-- ENVIRONMENT COMPLEXITY FACTORS -->
					<!-- ################################## -->
					<h2>Environment Complexity Factors</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>Factor</th>
							<th>Description</th>
							<th>Weight</th>
							<th>Perceived Impact</th>
							<th>
								Calculated Factor (weight*perceived
								impact)
							</th>
						</tr>
						<xsl:for-each
							select="environment-complexity-factors/environment-factor">
							<tr>
								<td>
									<xsl:value-of select="factor" />
								</td>
								<td>
									<xsl:value-of select="description" />
								</td>
								<td>
									<xsl:value-of select="weight" />
								</td>
								<td>
									<xsl:value-of select="impact" />
								</td>
								<td>
									<xsl:value-of
										select="calculated-factor" />
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td />
							<td />
							<td />
							<td>Total</td>
							<td>
								<b>
									<xsl:value-of
										select="environment-complexity-factors/total-value" />
								</b>
							</td>
						</tr>
					</table>
					<br />
					<hr />
					<h3>
						ECF (1.4 + (-0.03*Total Factor)) =
						<xsl:value-of
							select="environment-complexity-factors/calculated-ecf" />
					</h3>
					<hr />

					<!-- ################################## -->
					<!-- UNADJUSTED USECASE WEIGHTS -->
					<!-- ################################## -->
					<h2>Unadjusted Use Case Weights</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>Use Case Type</th>
							<th>Description</th>
							<th>Weight</th>
							<th>Number of Use Cases</th>
							<th>Result (weight*no. of use cases)</th>
						</tr>
						<xsl:for-each
							select="unadjusted-usecase-weights/usecase-weight">
							<tr>
								<td>
									<xsl:value-of select="type" />
								</td>
								<td>
									<xsl:value-of select="description" />
								</td>
								<td>
									<xsl:value-of select="weight" />
								</td>
								<td>
									<xsl:value-of select="num-cases" />
								</td>
								<td>
									<xsl:value-of select="result" />
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td />
							<td />
							<td />
							<td>Total UUCW</td>
							<td>
								<b>
									<xsl:value-of
										select="unadjusted-usecase-weights/total-UUCW" />
								</b>
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

					<hr />

					<!-- ################################## -->
					<!-- UNADJUSTED ACTOR WEIGHTS -->
					<!-- ################################## -->
					<h2>Unadjusted Actor Weights</h2>
					<hr />
					<table border="1">
						<tr bgcolor="#3399FF">
							<th>Actor Type</th>
							<th>Description</th>
							<th>Weight</th>
							<th>Number of Actors</th>
							<th>Result (weight*no. of actors)</th>
						</tr>
						<xsl:for-each
							select="unadjusted-actor-weights/actor-weight">
							<tr>
								<td>
									<xsl:value-of select="type" />
								</td>
								<td>
									<xsl:value-of select="description" />
								</td>
								<td>
									<xsl:value-of select="weight" />
								</td>
								<td>
									<xsl:value-of select="num-actors" />
								</td>
								<td>
									<xsl:value-of select="result" />
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<td />
							<td />
							<td />
							<td>Total UAW</td>
							<td>
								<b>
									<xsl:value-of
										select="unadjusted-actor-weights/total-UAW" />
								</b>
							</td>
						</tr>
					</table>
					<br />
					<hr />
					<h3>
						Total UAW =
						<xsl:value-of
							select="unadjusted-actor-weights/total-UAW" />
					</h3>
					<hr />
				</body>
			</font>
		</html>
	</xsl:template>
</xsl:stylesheet>

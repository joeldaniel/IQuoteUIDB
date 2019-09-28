package pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import utilities.CommonFunctions;

public class Negotiation extends CommonFunctions{
public static String RaghavHeader(String DB) {
		
		String s = "declare 	@IdQuote int,\r\n" + 
				"	@OptionDesc varchar(255),\r\n" + 
				"	@Customer varchar(255),\r\n" + 
				"	@SalesPerson varchar(255),\r\n" + 
				"	@PaymentTerm varchar(255),\r\n" + 
				"	@Title varchar(255),\r\n" + 
				"	@Contact varchar(255),\r\n" + 
				"	@CurrencySymbol varchar(255),\r\n" + 
				"	@Currency varchar(255),	\r\n" + 
				"	@Agency varchar(255), 	\r\n" + 
				"	@CostRefDate varchar(255),	\r\n" + 
				"	@Product varchar(255),\r\n" + 
				"	@TotalCmp int\r\n" + 
				"	declare @Table1Count int, @Table2Count int\r\n" + 
				"	declare @TempVar varchar(255)\r\n" + 
				"	SELECT @Table1Count= count(*) FROM iquoteuiautodb71."+DB+".dbo.TempTable\r\n" + 
				"	SELECT @Table2Count = count(*) FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"	if ( @Table1Count= @Table2Count)\r\n" + 
				"	BEGIN\r\n" + 
				"		DECLARE db_cursor CURSOR FOR \r\n" + 
				"		SELECT * \r\n" + 
				"		FROM iquoteuiautodb71."+DB+".dbo.TempTable\r\n" + 
				"		OPEN db_cursor  \r\n" + 
				"		FETCH NEXT FROM db_cursor INTO    \r\n" + 
				"			@IdQuote,\r\n" + 
				"			@OptionDesc,\r\n" + 
				"			@Customer,\r\n" + 
				"			@SalesPerson,\r\n" + 
				"			@PaymentTerm,\r\n" + 
				"			@Title,\r\n" + 
				"			@CurrencySymbol,\r\n" + 
				"			@Currency,\r\n" + 
				"			@Contact,	\r\n" + 
				"			@Agency, 	\r\n" + 
				"			@CostRefDate,	\r\n" + 
				"			@Product,\r\n" + 
				"			@TotalCmp	  \r\n" + 
				"		WHILE @@FETCH_STATUS = 0  \r\n" + 
				"		BEGIN  \r\n" + 
				"			 SELECT @TempVar = OptionDesc FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @OptionDesc <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in :'+@OptionDesc+' Base value is : '+@TempVar+' Actual value is : '+@OptionDesc\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Customer FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Customer <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Customer'+' Base value is : '+@TempVar+' Actual value is : '+@Customer\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = CostRefDate FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @CostRefDate <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : CostRefDate'+' Base value is : '+@TempVar+' Actual value is : '+@CostRefDate\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = SalesPerson FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @SalesPerson <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : SalesPerson'+' Base value is : '+@TempVar+' Actual value is : '+@SalesPerson\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = PaymentTerm FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @PaymentTerm <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : PaymentTerm'+' Base value is : '+@TempVar+' Actual value is : '+@PaymentTerm\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Title FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Title <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Title'+' Base value is : '+@TempVar+'Actual value is : '+@Title\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Contact FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Contact <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Contact'+' Base value is : '+@TempVar+' Actual value is : '+@Contact\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = CurrencySymbol FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @CurrencySymbol <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : CurrencySymbol'+' Base value is : '+@TempVar+' Actual value is : '+@CurrencySymbol\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Currency FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Currency <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Currency'+' Base value is : '+@TempVar+' Actual value is : '+@Currency\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Agency FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Agency <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Agency'+ 'Base value is : '+@TempVar+' Actual value is : '+@Agency\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = Product FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @Product <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : Product'+' Base value is : '+@TempVar+' ACtual value is : '+@Product\r\n" + 
				"			end\r\n" + 
				"			SELECT @TempVar = TotalCmp FROM iquotedbdbqry."+DB+".dbo.TempTable\r\n" + 
				"			if @TotalCmp <> @TempVar \r\n" + 
				"			begin\r\n" + 
				"				print 'Value MisMatched in : TotalCmp'+' Base value is : '+@TempVar+' Actual value is : '+@TotalCmp\r\n" + 
				"			end\r\n" + 
				"		    FETCH NEXT FROM db_cursor INTO 	@IdQuote,\r\n" + 
				"			@OptionDesc,\r\n" + 
				"			@Customer,\r\n" + 
				"			@SalesPerson,\r\n" + 
				"			@PaymentTerm,\r\n" + 
				"			@Title,\r\n" + 
				"			@Contact,\r\n" + 
				"			@CurrencySymbol,\r\n" + 
				"			@Currency,	\r\n" + 
				"			@Agency, 	\r\n" + 
				"			@CostRefDate,	\r\n" + 
				"			@Product,\r\n" + 
				"			@TotalCmp\r\n" + 
				"		END \r\n" + 
				"		CLOSE db_cursor  \r\n" + 
				"		DEALLOCATE db_cursor\r\n" + 
				"	END\r\n" + 
				"	ELSE\r\n" + 
				"	BEGIN\r\n" + 
				"		Print 'Mismatch with count';\r\n" + 
				"	END";
		return s;
	}
	static ResultSet rs;
	static HashMap<String,String> name = new HashMap<String,String>();
	
	public static String Header(String DB,String BaseDBserver,String ActualDBserver,String BaseEstimate,String ActualEstimate) {
		StringBuilder sbdr = new StringBuilder("Hello"); 
		String s ="DECLARE @BaseEst VARCHAR(10), @ActualEst VARCHAR(10)\r\n" + 
				"SET @BaseEst="+BaseEstimate+"\r\n" + 
				"SET @ActualEst="+ActualEstimate+"\r\n" + 
				"Begin\r\n" + 
				"Select (case when (Base.OptionDesc= Actual.OptionDesc) then 'Both are Equal' ELSE Base.OptionDesc + ' ' +Actual.OptionDesc  end) as OptionDesc,\r\n" + 
				"(case when (Base.Customer= Actual.Customer) then 'Both are Equal' ELSE Base.Customer + ' ' +Actual.Customer  end) as Customer,\r\n" + 
				"(case when (Base.SalesPerson= Actual.SalesPerson) then 'Both are Equal' ELSE Base.SalesPerson + ' ' +Actual.SalesPerson  end) as SalesPerson,\r\n" + 
				"(case when (Base.PaymentTerm= Actual.PaymentTerm) then 'Both are Equal' ELSE Base.PaymentTerm + ' ' +Actual.PaymentTerm  end) as PaymentTerm,\r\n" + 
				"(case when (Base.Title= Actual.Title) then 'Both are Equal' ELSE Base.Title + ' ' +Actual.Title  end) as Title,\r\n" + 
				"(case when (Base.CurrencySymbol= Actual.CurrencySymbol) then 'Both are Equal' ELSE Base.CurrencySymbol + ' ' +Actual.CurrencySymbol  end) as CurrencySymbol,\r\n" + 
				"(case when (Base.Currency= Actual.Currency) then 'Both are Equal' ELSE Base.Currency + ' ' +Actual.Currency  end) as Currency,\r\n" + 
				"(case when (Base.Contact= Actual.Contact) then 'Both are Equal' ELSE Base.Contact + ' ' +Actual.Contact  end) as Contact,\r\n" + 
				"(case when (Base.Agency is null and  Actual.Agency is null) then 'Both are Equal' ELSE Base.Agency + ' ' +Actual.Agency  end) as Agency,\r\n" + 
				"(case when (Base.CostRefDate= Actual.CostRefDate) then 'Both are Equal' ELSE cast(Base.CostRefDate as varchar(50)) + ' ' +cast(Actual.CostRefDate as varchar(50))  end) as CostRefDate,\r\n" + 
				"(case when (Base.Product= Actual.Product) then 'Both are Equal' ELSE Base.Product + ' ' +Actual.Product  end) as Product,\r\n" + 
				"(case when (Base.TotalCmp= Actual.TotalCmp) then 'Both are Equal' ELSE cast(Base.TotalCmp as varchar(50)) + ' ' +cast(Actual.TotalCmp as varchar(50))  end) as TotalCmp\r\n" + 
				" from (SELECT \r\n" + 
				"				OptionDesc,\r\n" + 
				"                Customer,\r\n" + 
				"                SalesPerson,\r\n" + 
				"                PaymentTerm,\r\n" + 
				"                Title,\r\n" + 
				"                CurrencySymbol,\r\n" + 
				"                Currency,            \r\n" + 
				"                Contact,\r\n" + 
				"                Agency,                \r\n" + 
				"                CostRefDate,     \r\n" + 
				"                Product,\r\n" + 
				"                SUM(PscmpQtyTotal) TotalCmp                            \r\n" + 
				"FROM ( SELECT  \r\n" + 
				"                                   qttTMQuote.IdQuote                    AS IdQuote,  \r\n" + 
				"                                   qttTMQuote.Description                AS Title,  \r\n" + 
				"                                   qttTMQuote.InsUser                    AS InsUser,  \r\n" + 
				"                                   qttTMQuote.UpdUser                    AS UpdUser,  \r\n" + 
				"                                   qttTMQuote.InsDt                      AS InsDtQuote,  \r\n" + 
				"                                   CustomerService.FullName              AS SalesPerson,  \r\n" + 
				"                                   Customer.Name                         AS Customer,  \r\n" + 
				"                                   CustomerContact.Name                  AS Contact,  \r\n" + 
				"                                   Brok.Name                             AS Agency,  \r\n" + 
				"                                   qttTMSalesRule.PriceTableRefDt        AS PriceTableRefDt,  \r\n" + 
				"                                   qttTMSalesRule.CostTableRefDt         AS CostRefDate,  \r\n" + 
				"                                   genTCPaymentForm.Description          AS PaymentTerm,  \r\n" + 
				"                                   genTCCurrency.Description             AS Currency,  \r\n" + 
				"                                   genTCCurrency.CurrencySymbol          AS CurrencySymbol,  \r\n" + 
				"                                   qttTCPrdGroup.Description             AS Product,  \r\n" + 
				"                                   qttTMItemOptionQty.ItemOptionQtyOrder AS OptQtd,  \r\n" + 
				"                                   qttTMItemOptionQty.IdItemOptionQty    AS IdItemOptionQty,  \r\n" + 
				"                                   qttTMItemOption.IdItemOption          AS IdItemOption,  \r\n" + 
				"                                   qttTMItemOptionQtyPSCmp.PSCmpQtyTotal,  \r\n" + 
				"                                   qttTMItemOption.Description           AS OptionDesc                                 \r\n" + 
				"                                FROM ["+BaseDBserver+"].["+DB+"].[dbo].qttTMQuote                      WITH (NOLOCK)                               \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].crmTCActor CustomerService WITH  (NOLOCK)ON CustomerService.IdActor = qttTMQuote.IdCustomerService                                 \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].crmTCActor Customer      WITH   (NOLOCK)ON Customer.IdActor = qttTMQuote.IdCustomer                               \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].crmTCActor CustomerContact WITH  (NOLOCK)ON CustomerContact.IdActor = qttTMQuote.IdCustomerContact                              \r\n" + 
				"                                LEFT JOIN ["+BaseDBserver+"].["+DB+"].[dbo].crmTCActor Brok          WITH    (NOLOCK)ON Brok.IdActor = qttTMQuote.IdBroker  \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTMItem               WITH    (NOLOCK)ON qttTMItem.IdQuote = qttTMQuote.IdQuote  \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTMItemOption          WITH   (NOLOCK)ON qttTMItemOption.IdItem = qttTMItem.IdItem  \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTMItemOptionQty       WITH   (NOLOCK)ON qttTMItemOptionQty.IdItemOption = qttTMItemOption.IdItemOption  \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTMItemOptionQtyPSCmp  WITH   (NOLOCK)ON qttTMItemOptionQtyPSCmp.IdItemOptionQty = qttTMItemOptionQty.IdItemOptionQty                              \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTMSalesRule           WITH   (NOLOCK)ON qttTMSalesRule.IdSalesRule = qttTMItem.IdSalesRule                            \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].genTCPaymentForm        WITH    (NOLOCK)ON genTCPaymentForm.IdPaymentForm = qttTMSalesRule.IdPaymentForm                            \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].genTCCurrency             WITH  (NOLOCK)ON genTCCurrency.IdCurrency = qttTMSalesRule.IdCurrency                             \r\n" + 
				"                                INNER JOIN ["+BaseDBserver+"].["+DB+"].[dbo].qttTCPrdGroup           WITH    (NOLOCK)ON qttTCPrdGroup.IdPrdGroup = qttTMQuote.IDPrdGroup ) qttSCHeaderDemQuote\r\n" + 
				"WHERE\r\n" + 
				"                IdQuote = @BaseEst\r\n" + 
				"GROUP BY    \r\n" + 
				"                OptionDesc,\r\n" + 
				"                Customer,\r\n" + 
				"                SalesPerson,\r\n" + 
				"                PaymentTerm,\r\n" + 
				"                Title,\r\n" + 
				"                Currency,\r\n" + 
				"                CurrencySymbol,\r\n" + 
				"                Contact,\r\n" + 
				"                Agency,                \r\n" + 
				"                CostRefDate,     \r\n" + 
				"                Product    ) Base\r\n" + 
				"                                                          LEFT JOIN\r\n" + 
				"                                                          (SELECT   \r\n" + 
				"				 OptionDesc,\r\n" + 
				"                Customer,\r\n" + 
				"                SalesPerson,\r\n" + 
				"                PaymentTerm,\r\n" + 
				"                Title,\r\n" + 
				"                CurrencySymbol,\r\n" + 
				"                Currency,            \r\n" + 
				"                Contact,\r\n" + 
				"                Agency,                \r\n" + 
				"                CostRefDate,     \r\n" + 
				"                Product,\r\n" + 
				"                SUM(PscmpQtyTotal) TotalCmp                             \r\n" + 
				"FROM ( SELECT  \r\n" + 
				"                                   qttTMQuote.IdQuote                    AS IdQuote,  \r\n" + 
				"                                   qttTMQuote.Description                AS Title,  \r\n" + 
				"                                   qttTMQuote.InsUser                    AS InsUser,  \r\n" + 
				"                                   qttTMQuote.UpdUser                    AS UpdUser,  \r\n" + 
				"                                   qttTMQuote.InsDt                      AS InsDtQuote,  \r\n" + 
				"                                   CustomerService.FullName              AS SalesPerson,  \r\n" + 
				"                                   Customer.Name                         AS Customer,  \r\n" + 
				"                                   CustomerContact.Name                  AS Contact,  \r\n" + 
				"                                   Brok.Name                             AS Agency,  \r\n" + 
				"                                   qttTMSalesRule.PriceTableRefDt        AS PriceTableRefDt,  \r\n" + 
				"                                   qttTMSalesRule.CostTableRefDt         AS CostRefDate,  \r\n" + 
				"                                   genTCPaymentForm.Description          AS PaymentTerm,  \r\n" + 
				"                                   genTCCurrency.Description             AS Currency,  \r\n" + 
				"                                   genTCCurrency.CurrencySymbol          AS CurrencySymbol,  \r\n" + 
				"                                   qttTCPrdGroup.Description             AS Product,  \r\n" + 
				"                                   qttTMItemOptionQty.ItemOptionQtyOrder AS OptQtd,  \r\n" + 
				"                                   qttTMItemOptionQty.IdItemOptionQty    AS IdItemOptionQty,  \r\n" + 
				"                                   qttTMItemOption.IdItemOption          AS IdItemOption,  \r\n" + 
				"                                   qttTMItemOptionQtyPSCmp.PSCmpQtyTotal,  \r\n" + 
				"                                   qttTMItemOption.Description           AS OptionDesc                                   \r\n" + 
				"                                FROM ["+ActualDBserver+"].["+DB+"].[dbo].qttTMQuote                      WITH (NOLOCK)                               \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].crmTCActor CustomerService WITH  (NOLOCK)ON CustomerService.IdActor = qttTMQuote.IdCustomerService                               \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].crmTCActor Customer      WITH   (NOLOCK)ON Customer.IdActor = qttTMQuote.IdCustomer                              \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].crmTCActor CustomerContact WITH  (NOLOCK)ON CustomerContact.IdActor = qttTMQuote.IdCustomerContact                                \r\n" + 
				"                                LEFT JOIN  ["+ActualDBserver+"].["+DB+"].[dbo].crmTCActor Brok          WITH    (NOLOCK)ON Brok.IdActor = qttTMQuote.IdBroker  \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTMItem               WITH    (NOLOCK)ON qttTMItem.IdQuote = qttTMQuote.IdQuote  \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTMItemOption          WITH   (NOLOCK)ON qttTMItemOption.IdItem = qttTMItem.IdItem  \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTMItemOptionQty       WITH   (NOLOCK)ON qttTMItemOptionQty.IdItemOption = qttTMItemOption.IdItemOption  \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTMItemOptionQtyPSCmp  WITH   (NOLOCK)ON qttTMItemOptionQtyPSCmp.IdItemOptionQty = qttTMItemOptionQty.IdItemOptionQty                               \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTMSalesRule           WITH   (NOLOCK)ON qttTMSalesRule.IdSalesRule = qttTMItem.IdSalesRule                               \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].genTCPaymentForm        WITH    (NOLOCK)ON genTCPaymentForm.IdPaymentForm = qttTMSalesRule.IdPaymentForm                            \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].genTCCurrency             WITH  (NOLOCK)ON genTCCurrency.IdCurrency = qttTMSalesRule.IdCurrency                              \r\n" + 
				"                                INNER JOIN ["+ActualDBserver+"].["+DB+"].[dbo].qttTCPrdGroup           WITH    (NOLOCK)ON qttTCPrdGroup.IdPrdGroup = qttTMQuote.IDPrdGroup ) qttSCHeaderDemQuote\r\n" + 
				"WHERE\r\n" + 
				"                IdQuote = @ActualEst\r\n" + 
				"GROUP BY    \r\n" + 
				"                OptionDesc,\r\n" + 
				"                Customer,\r\n" + 
				"                SalesPerson,\r\n" + 
				"                PaymentTerm,\r\n" + 
				"                Title,\r\n" + 
				"                Currency,\r\n" + 
				"                CurrencySymbol,\r\n" + 
				"                Contact,\r\n" + 
				"                Agency,                \r\n" + 
				"                CostRefDate,     \r\n" + 
				"                Product    ) Actual ON                                                          \r\n" + 
				"                                                          Base.OptionDesc=Actual.OptionDesc OR \r\n" + 
				"                                                          Base.Customer=Actual.Customer OR \r\n" + 
				"                                                          Base.SalesPerson=Actual.SalesPerson OR\r\n" + 
				"                                                         Base.PaymentTerm=Actual.PaymentTerm OR\r\n" + 
				"                                                          Base.Title=Actual.Title OR\r\n" + 
				"                                                         Base.CurrencySymbol=Actual.CurrencySymbol OR\r\n" + 
				"                                                          Base.Currency=Actual.Currency OR\r\n" + 
				"                                                          Base.Contact=Actual.Contact OR\r\n" + 
				"                                                          Base.Agency=Actual.Agency OR\r\n" + 
				"                                                         Base.CostRefDate=Actual.CostRefDate OR\r\n" + 
				"                                                          Base.Product= Actual.Product OR\r\n" + 
				"                                                          Base.TotalCmp= Actual.TotalCmp\r\n" + 
				"end";
		
		return s;
		
	}
	
	public static void VerifyHeader(String DB,String BaseDBserver,String ActualDBserver,String BaseEstimate,String ActualEstimate) {
		Properties prop=initialize_properties("Header");
		String query=prop.getProperty("Query");
		boolean flag=true;
		try {
			//query=;
			//iqdb.Createconnection("jdbc:sqlserver://"+BaseDBserver+":1433;databaseName="+DB+"", "iquote", "1quot3p@ss");
			rs=iqdb.RunQuery(query.replace("+BaseEstimate+", BaseEstimate).replace("+ActualEstimate+", ActualEstimate).replace("+ActualDBserver+", ActualDBserver).replace("+BaseDBserver+", BaseDBserver).replace("+DB+", DB));
			while(rs.next()) {
				String OptionDesc=rs.getString("OptionDesc");
				String Customer=rs.getString("Customer");
				String SalesPerson=rs.getString("SalesPerson");
				String PaymentTerm=rs.getString("PaymentTerm");
				String Title=rs.getString("Title");
				String CurrencySymbol=rs.getString("CurrencySymbol");
				String Currency=rs.getString("Currency");
				String Contact=rs.getString("Contact");
				String Agency=rs.getString("Agency");
				//String CostRefDate=rs.getString("CostRefDate");
				String Product=rs.getString("Product");
				String TotalCmp=rs.getString("TotalCmp");
				
				name.put("OptionDesc", OptionDesc != null ? OptionDesc : "");
				name.put("Customer", Customer != null ? Customer : "");
				name.put("SalesPerson", SalesPerson != null ? SalesPerson : "");
				name.put("PaymentTerm", PaymentTerm != null ? PaymentTerm : "");
				name.put("Title", Title != null ? Title : "");
				name.put("CurrencySymbol", CurrencySymbol != null ? CurrencySymbol : "");
				name.put("Currency", Currency != null ? Currency : "");
				name.put("Contact", Contact != null ? Contact : "");
				name.put("Agency", Agency != null ? Agency : "");
				//name.put("CostRefDate", CostRefDate != null ? CostRefDate : "");
				name.put("Product", Product != null ? Product : "");
				name.put("TotalCmp", TotalCmp != null ? TotalCmp : "");
				
				  for (Map.Entry<String,String> entry : name.entrySet())  
			           if(!entry.getValue().contains("Both are Equal")) {
			        	   System.out.println(entry.getKey()+" Has mismatched value");
			        	   flag=false;
			        	   break;
			           }
				if(flag)
					System.out.println("All column values matched in header query");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
}

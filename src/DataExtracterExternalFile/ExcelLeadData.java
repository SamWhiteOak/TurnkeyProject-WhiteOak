package DataExtracterExternalFile;
import com.poiji.annotation.ExcelCellName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelLeadData {

        @ExcelCellName("turnkeyId")
        private String turnkeyId;

        @ExcelCellName("turnkeyStatus")
        private String turnkeyStatus;

        @ExcelCellName("salutation")
        private String salutation;

        @ExcelCellName("firstName")
        private String firstName;

        @ExcelCellName("lastName")
        private String lastName;

        @ExcelCellName("phone")
        private String phone;

        @ExcelCellName("email")
        private String email;

        @ExcelCellName("birthDate")
        private String birthDate;

        @ExcelCellName("companyName")
        private String companyName;

        @ExcelCellName("companyRegistrationNumber")
        private String companyRegistrationNumber;

        @ExcelCellName("softSearchScore")
        private String softSearchScore;

        @ExcelCellName("softSearchScoreBand")
        private String softSearchScoreBand;

        @ExcelCellName("websiteName")
        private String websiteName;

        @ExcelCellName("amount")
        private String amount;

        @ExcelCellName("financeTerm")
        private String financeTerm;

        @ExcelCellName("financeProduct")
        private String financeProduct;

        @ExcelCellName("tradingHouseNumberName")
        private String tradingHouseNumberName;

        @ExcelCellName("tradingAddressLine1")
        private String tradingAddressLine1;

        @ExcelCellName("tradingAddressLine2")
        private String tradingAddressLine2;

        @ExcelCellName("tradingCounty")
        private String tradingCounty;

        @ExcelCellName("tradingTownCity")
        private String tradingTownCity;

        @ExcelCellName("tradingPostCode")
        private String tradingPostCode;

        @ExcelCellName("yearEstablished")
        private String yearEstablished;

        @ExcelCellName("consentedToTOB")
        private Boolean consentedToTOB;

        @ExcelCellName("isLandingPageLead")
        private Boolean isLandingPageLead;

        @ExcelCellName("turnkeyCreatedByUserEmail")
        private Boolean turnkeyCreatedByUserEmail;

        @ExcelCellName("ipAddress")
        private String ipAddress;

        @ExcelCellName("gclid")
        private String gclid;

        @ExcelCellName("lastClickCampaign")
        private String lastClickCampaign;

        @ExcelCellName("lastClickSource")
        private String lastClickSource;

        @ExcelCellName("lastClickKeyword")
        private String lastClickKeyword;

        @ExcelCellName("lastClickMedium")
        private String lastClickMedium;

        @ExcelCellName("lastClickTerm")
        private String lastClickTerm;

        // Add getters and setters here (or use Lombok to reduce boilerplate)

        // Getters and Setters

        public String getTurnkeyId() { return turnkeyId; }
        public void setTurnkeyId(String turnkeyId) { this.turnkeyId = turnkeyId; }

        public String getTurnkeyStatus() { return turnkeyStatus; }
        public void setTurnkeyStatus(String turnkeyStatus) { this.turnkeyStatus = turnkeyStatus; }

        public String getSalutation() { return salutation; }
        public void setSalutation(String salutation) { this.salutation = salutation; }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getBirthDate() { return birthDate; }
        public void setBirthDate(String birthDate) {this.birthDate = birthDate; }

        public String getCompanyName() { return companyName; }
        public void setCompanyName(String companyName) { this.companyName = companyName; }

        public String getCompanyRegistrationNumber() { return companyRegistrationNumber; }
        public void setCompanyRegistrationNumber(String companyRegistrationNumber) { this.companyRegistrationNumber = companyRegistrationNumber; }

        public String getSoftSearchScore() { return softSearchScore; }
        public void setSoftSearchScore(String softSearchScore) { this.softSearchScore = softSearchScore; }

        public String getSoftSearchScoreBand() { return softSearchScoreBand; }
        public void setSoftSearchScoreBand(String softSearchScoreBand) { this.softSearchScoreBand = softSearchScoreBand; }

        public String getWebsiteName() { return websiteName; }
        public void setWebsiteName(String websiteName) { this.websiteName = websiteName; }

        public String getAmount() { return amount; }
        public void setAmount(String amount) { this.amount = amount; }

        public String getFinanceTerm() { return financeTerm; }
        public void setFinanceTerm(String financeTerm) { this.financeTerm = financeTerm; }

        public String getFinanceProduct() { return financeProduct; }
        public void setFinanceProduct(String financeProduct) { this.financeProduct = financeProduct; }

        public String getTradingHouseNumberName() { return tradingHouseNumberName; }
        public void setTradingHouseNumberName(String tradingHouseNumberName) { this.tradingHouseNumberName = tradingHouseNumberName; }

        public String getTradingAddressLine1() { return tradingAddressLine1; }
        public void setTradingAddressLine1(String tradingAddressLine1) { this.tradingAddressLine1 = tradingAddressLine1; }

        public String getTradingAddressLine2() { return tradingAddressLine2; }
        public void setTradingAddressLine2(String tradingAddressLine2) { this.tradingAddressLine2 = tradingAddressLine2; }

        public String getTradingCounty() { return tradingCounty; }
        public void setTradingCounty(String tradingCounty) { this.tradingCounty = tradingCounty; }

        public String getTradingTownCity() { return tradingTownCity; }
        public void setTradingTownCity(String tradingTownCity) { this.tradingTownCity = tradingTownCity; }

        public String getTradingPostCode() { return tradingPostCode; }
        public void setTradingPostCode(String tradingPostCode) { this.tradingPostCode = tradingPostCode; }

        public String getYearEstablished() { return yearEstablished; }
        public void setYearEstablished(String yearEstablished) { this.yearEstablished = yearEstablished; }

        public Boolean getConsentedToTOB() { return consentedToTOB; }
        public void setConsentedToTOB(Boolean consentedToTOB) { this.consentedToTOB = consentedToTOB; }

        public Boolean getIsLandingPageLead() { return isLandingPageLead; }
        public void setIsLandingPageLead(Boolean isLandingPageLead) { this.isLandingPageLead = isLandingPageLead; }

        public Boolean getturnkeyCreatedByUserEmail() { return turnkeyCreatedByUserEmail; }
        public void setturnkeyCreatedByUserEmail(Boolean turnkeyCreatedByUserEmail) { this.turnkeyCreatedByUserEmail = turnkeyCreatedByUserEmail; }

        public String getIpAddress() { return ipAddress; }
        public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

        public String getGclid() { return gclid; }
        public void setGclid(String gclid) { this.gclid = gclid; }

        public String getLastClickCampaign() { return lastClickCampaign; }
        public void setLastClickCampaign(String lastClickCampaign) { this.lastClickCampaign = lastClickCampaign; }

        public String getLastClickSource() { return lastClickSource; }
        public void setLastClickSource(String lastClickSource) { this.lastClickSource = lastClickSource; }

        public String getLastClickKeyword() { return lastClickKeyword; }
        public void setLastClickKeyword(String lastClickKeyword) { this.lastClickKeyword = lastClickKeyword; }

        public String getLastClickMedium() { return lastClickMedium; }
        public void setLastClickMedium(String lastClickMedium) { this.lastClickMedium = lastClickMedium; }

        public String getLastClickTerm() { return lastClickTerm; }
        public void setLastClickTerm(String lastClickTerm) { this.lastClickTerm = lastClickTerm; }
    }


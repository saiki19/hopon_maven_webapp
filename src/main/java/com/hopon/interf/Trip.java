package com.hopon.interf;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hopon.dto.*;
import com.hopon.utils.ConfigurationException;

public interface Trip {
	UserRegistrationDTO loadUserRegistration(Connection con, String category,UserRegistrationDTO userRegistrationDTO, String senderId) throws ConfigurationException;
	CompanyRegisterDTO loadCompanyRegistration(Connection con, String category,CompanyRegisterDTO companyRegisterDTO) throws ConfigurationException;
	UserRegistrationDTO validateUser(Connection con, UserRegistrationDTO userDTO) throws ConfigurationException;
	UserRegistrationDTO closeUserAcount(Connection con, UserRegistrationDTO userDTO, String senderId) throws ConfigurationException;
	List<CircleDTO> getallCircles(Connection con , String userid) throws ConfigurationException;
    List<CircleDTO> getallMemberCircles(Connection con , String userid) throws ConfigurationException;
    List<CircleDTO> getallRegisteredCircles(Connection con) throws ConfigurationException;
    List<CircleDTO> getallRegisteredTaxiCircles(Connection con, String prefix) throws ConfigurationException;
    List<CircleDTO> getallRegisteredNonTaxiCircles(Connection con, String prefix) throws ConfigurationException;
    List<CompanyRegisterDTO> allCompanyList(Connection con)throws ConfigurationException; 
    RideManagementDTO loadRide(Connection con, String category ,RideManagementDTO rideManagementDTO)throws ConfigurationException;
    RideManagementDTO loadRideSeeker(Connection con, String category ,RideManagementDTO rideSeekerDTO)throws ConfigurationException;
    FrequencyDTO loadFrequency(Connection con, String category,FrequencyDTO frequencyDTO) throws ConfigurationException;
    List<RideManagementDTO> loadAllRidemanagement(Connection con , String userName)throws ConfigurationException; 
    FavoritePlacesDTO loadFavoritePlaces(Connection con , FavoritePlacesDTO favoritePlacesDTO)throws ConfigurationException;
    CircleDTO loadCircle(Connection con , CircleDTO circleDTO)throws ConfigurationException;
    VehicleMasterDTO loadVehicle(Connection con, String category,VehicleMasterDTO vehicleMasterDTO) throws ConfigurationException;
    List<RideSeekerDTO> loadAllRideSeeker(Connection con , String userName)throws ConfigurationException; 
    List<PoolRequestsDTO> loadAllPoolRequest(Connection con , String userName)throws ConfigurationException;
    List<PoolRequestsDTO> loadAllPoolRequestSeeker(Connection con , String userName)throws ConfigurationException;
    List<CityDTO> loadAllCity(Connection con ,String finalValue)throws ConfigurationException;
    List<FavoritePlacesDTO> loadAllPlaces(Connection con,String usreName)throws ConfigurationException;
    CircleOwnerDTO loadCircleOwner (Connection con ,CircleOwnerDTO circleOwnerDTO, String senderId)throws ConfigurationException;
    CircleOwnerDTO updateCircleOwner (Connection con ,CircleOwnerDTO circleOwnerDTO, String senderId)throws ConfigurationException;
    List<CircleOwnerManagerDTO> loadallCircleForLoginUser(Connection con , String userID)throws ConfigurationException;
    List<CircleOwnerManagerDTO> loadallCircleMemberForLoginUser(Connection con , String userID)throws ConfigurationException;
    List<CircleOwnerManagerDTO> loadallPendingCircleMemberForLoginUser(Connection con , String userID)throws ConfigurationException;
    List<CircleOwnerManagerDTO> loadallLoginUserMessage(Connection con , String userID)throws ConfigurationException;
    List<CircleDTO> loadallCircleInvitationUserList(Connection con , String userID)throws ConfigurationException;
    List<MatchedTripDTO> loasAllMatchTripByCondition(Connection con , String startPoint ,String endPoint,String rideDate, int circleId)throws ConfigurationException;
    List<CombineRideDTO> getAllCombineVehicleList(Connection con , String startPoint ,String endPoint,String rideDate, int circleId)throws ConfigurationException;
    CircleMemberDTO loadConfirmOrDeclineUser(Connection con ,CircleMemberDTO userdto, String senderId)throws ConfigurationException;
    List<VehicleMasterDTO> loadAllVehicle(Connection con,String userID)throws ConfigurationException;
    RideManagementDTO loadCancleRide(Connection con ,RideManagementDTO rideManagementDTO)throws ConfigurationException;
    RideSeekerDTO loadCancleRideSeeker(Connection con ,RideSeekerDTO rideSeekerDTO)throws ConfigurationException;
    PoolRequestsDTO loadRateAndWriteNotes(Connection con , PoolRequestsDTO pooldto)throws ConfigurationException;
    CircleDTO loadDeclineCircle(Connection con,CircleDTO circleDTO, String userId)throws ConfigurationException;
    List<CircleDTO> loadCircleByName(Connection con ,String circleName, String userId)throws ConfigurationException;
    List<CompanyRegisterDTO> loadCompanyForLoginUser(Connection con,String userID)throws ConfigurationException;
    UserRegistrationDTO loadupdateUser(Connection con,UserRegistrationDTO userRegistrationDTO, String senderId)throws ConfigurationException;
    UserRegistrationDTO loadupdateUserPassword(Connection con,UserRegistrationDTO userRegistrationDTO, String senderId)throws ConfigurationException;
    CircleMemberDTO loadCirclemember(Connection con ,CircleMemberDTO circleMemberDTO, String senderId)throws ConfigurationException;
    List<UserRegistrationDTO> loadAllUser(Connection con,String prifex, int limit)throws ConfigurationException;
    CompanyRegisterDTO loadUpdateCompany(Connection con ,CompanyRegisterDTO companyRegisterDTO)throws ConfigurationException;
    PoolRequestsDTO loadInsertinPool(Connection con,PoolRequestsDTO poolRequestsDTO)throws ConfigurationException;
    VehicleMasterDTO loadUpdateSeat(Connection con,VehicleMasterDTO vehicleMasterDTO)throws ConfigurationException;
    MatchedTripDTO loasAllMatchTripByCondition1(Connection con , String vehicleID,String startTime ,String company)throws ConfigurationException;
    List<RideManagementDTO> loadMethodForPopupOne(Connection con,String vehicleID,String startTime)throws ConfigurationException;
    List<RideSeekerDTO> loadAllSeekerForRideID(Connection con,String rideID)throws ConfigurationException;
    VehicleMasterDTO loadupdateVehicle(Connection con,VehicleMasterDTO vehicleMasterDTO)throws ConfigurationException;
    VehicleMasterDTO loadupdateDefaultVehicle(Connection con,VehicleMasterDTO vehicleMasterDTO)throws ConfigurationException;
    VehicleMasterDTO loadInActivateVehicle(Connection con,VehicleMasterDTO vehicleMasterDTO)throws ConfigurationException;
    List<MessageBoardDTO> loadUnreadMessage(Connection con, String userid, String status)throws ConfigurationException;
    MessageBoardDTO loadInsertedMessage(Connection con, MessageBoardDTO messagedto)throws ConfigurationException;
    List<MessageBoardDTO> loadEmailSendingMessage(Connection con)throws ConfigurationException;
    List<MessageBoardDTO> loadSmsSendingMessage(Connection con)throws ConfigurationException;
    List<MessageBoardDTO> loadPopupMessage(Connection con, int userId)throws ConfigurationException;
	void loadMakeEmailStatusSent(Connection con, int[] messageId)throws ConfigurationException;
	boolean testVehicleRegistrationNumber(Connection con, String registrationNo) throws ConfigurationException;
	boolean testEmail(Connection con, String email) throws ConfigurationException;
	boolean testUniqueMobileNumber(Connection con, String mobileNo, int excludedUserId) throws ConfigurationException;
	String testEmailAllStatus(Connection con, String email) throws ConfigurationException;
	RideManagementDTO getRideManagerPopupData(Connection con, String rideID)throws ConfigurationException;
	RideManagementDTO getRideManagerPopupDataDirect(Connection con, String rideID)throws ConfigurationException;
	VehicleMasterDTO getVehicleDtoById(Connection con, String vehicleID)throws ConfigurationException;
	List<CircleDTO> getAllAffiliatedTaxiCircle(Connection con, String prefix) throws ConfigurationException;
	void getMakeTaxiCircleAffiliated(Connection con, int parentCircleId, int childCircleId)throws ConfigurationException;
	List<CircleAffiliationsDTO> getAllAffiliatedCircle(Connection con, String circleId)throws ConfigurationException;
	List<CircleAffiliationsDTO> getAllPendingCircle(Connection con, String circleId)throws ConfigurationException;
	void getMakeTaxiCircleAffiliatedActive(Connection con, int parentCircleId, int childCircleId)throws ConfigurationException;
	void getMakeTaxiCircleAffiliatedInactive(Connection con, int parentCircleId, int childCircleId)throws ConfigurationException;
	CircleDTO getCircleDtoByCircleId(Connection con, int circleId)throws ConfigurationException;
	List<CircleAffiliationsDTO> getAllPendingAffiliatedCircle(Connection con, int userId)throws ConfigurationException;
	List<CircleDTO> getAffiliateCircleForTaxiUser(Connection con, int userId)throws ConfigurationException;
	List<MatchedTripDTO> loadRidesByCircle(Connection con, String circleId)throws ConfigurationException;
	List<RideSeekerDTO> loadCompletedRideSeekerList(Connection con)throws ConfigurationException;
	List<RideManagementDTO> loadCompletedRideManagementList(Connection con)throws ConfigurationException;
	void updateMessageStatusRead(Connection con, int[] messageId) throws ConfigurationException;
	void updateMessageStatusDelete(Connection con, int[] messageId) throws ConfigurationException;
	UserRegistrationDTO loadForgotPasswordUser(Connection con, String userName, String randomPassword, String senderId) throws ConfigurationException;
	UserRegistrationDTO loadAverageRatingForUser(Connection con, UserRegistrationDTO dto) throws ConfigurationException;
	List<UserRegistrationDTO> loadForgotPasswordUser(Connection con, List<Integer> userId) throws ConfigurationException;
	List<UserRegistrationDTO> loadAlluserForACircle(Connection con , String userID)throws ConfigurationException;
	RideSeekerDTO loadRideSeekerData(Connection con, int rideSeekerId)throws ConfigurationException;
	boolean checkRideSeekerDuplicacy(Connection con, RideManagementDTO rideSeekerDTO)throws ConfigurationException;
	boolean checkDuplicacy(Connection con, RideManagementDTO rideSeekerDTO)throws ConfigurationException;
	List<Integer> findAllUsersInCircle(Connection con, int circleID)throws ConfigurationException;
	CompanyRegisterDTO getCompanyUpdateByUserId(Connection con, CompanyRegisterDTO companyRegisterDTO) throws ConfigurationException;
	CircleDTO deactivateCircle(Connection con, CircleDTO circleDTO) throws ConfigurationException;
	void makeCircleInactiveForUser(Connection con, UserRegistrationDTO userRegistrationDto) throws ConfigurationException;
	void makeCircleMemberInactiveForUser(Connection con, UserRegistrationDTO userRegistrationDto, String senderId) throws ConfigurationException;
	void makeCircleOwnerForUser(Connection con, UserRegistrationDTO userRegistrationDto, String senderId) throws ConfigurationException;
	void makeRideSeekerCancelForUser(Connection con, UserRegistrationDTO userRegistrationDto) throws ConfigurationException;
	void makeRideCancelForUser(Connection con, UserRegistrationDTO userRegistrationDto) throws ConfigurationException;
	UserPreferencesDTO getUserPreferencesSave(Connection con, UserPreferencesDTO userPreferencesDTO) throws ConfigurationException;
	UserPreferencesDTO getUserPreferencesEdit(Connection con, UserPreferencesDTO userPreferencesDTO) throws ConfigurationException;
	UserPreferencesDTO getUserPreferences(Connection con, int userId) throws ConfigurationException;
	List<ManageRideDTO> findCompletedManageRide(Connection con, ManageRideFormDTO manageRideFormDTO)throws ConfigurationException;
	List<ManageRideDTO> findPendingManageRide(Connection con, ManageRideFormDTO manageRideFormDTO)throws ConfigurationException;
	List<ManageRideDTO> findPendingMatchedManageRide(Connection con, ManageRideFormDTO manageRideFormDTO)throws ConfigurationException;
	void getUpdatePoolForIsResult(Connection con)throws ConfigurationException;
	List<MatchedTripDTO> findMatchTripByGroupId(Connection con, List<String> groupId)	throws ConfigurationException;
	int calculateSingleRide(Connection con, List<Integer> seekerId) throws ConfigurationException;
	ApproverDTO addApprover(Connection con, ApproverDTO approberdto)throws ConfigurationException;
	ApproverDTO editApprover(Connection con, ApproverDTO approberdto)throws ConfigurationException;
	List<ApproverDTO> findApproverForUser(Connection con, int userId)throws ConfigurationException;
	List<RidePreVehicle> fetchRidePreVehicleList(Connection con, String date, int userId)throws ConfigurationException;
	ApproverDTO findApproverById(Connection con, int approverId)throws ConfigurationException;
	UserRegistrationDTO findUserByEmail(Connection con, String emailId)throws ConfigurationException;
	SmsReplyDTO addSms(Connection con, SmsReplyDTO dto)throws ConfigurationException;
	List<RideSeekerDTO> findRideSeekerDataByIds(Connection con, List<Integer> seekerIds)throws ConfigurationException;
	RideSeekerDTO changeField(Connection con, RideSeekerDTO rideSeekerDTO)throws ConfigurationException;
	void changePoolRequestStatusForRideGiver(Connection con, int rideId) throws ConfigurationException;
	void changePoolRequestStatusForRideTaker(Connection con, int rideSeekerId) throws ConfigurationException;
	UserRegistrationDTO findDriverDtoByRideId(Connection con, String rideId) throws ConfigurationException;
	UserRegistrationDTO getUserById(Connection con, int userId) throws ConfigurationException;
	UserRegistrationDTO findUserDtoByVehicleId(Connection con, int vehicleId) throws ConfigurationException;
	List<RideSeekerDTO> findRideSeekerDetailsForApprove(Connection con, String email_id) throws ConfigurationException;
	void changeStatus(Connection con, int seekerId, String status) throws ConfigurationException;
	CityDTO loadCity(Connection con, String cityName, String stateName) throws ConfigurationException;
	VehicleMasterDTO getVehicleDtoByRideId(Connection con, int rideId) throws ConfigurationException;
	PageStoreDTO getPageStoreByCode(Connection con, String code) throws ConfigurationException;
	//Login page
	void insertLoginPage(Connection con, LoginPageDTO dto)throws ConfigurationException;
	List<LoginPageDTO> getLoginPagesByUserId(Connection con, int userId)throws ConfigurationException;
	void updateLoginPageByUserId(Connection con, LoginPageDTO dto)throws ConfigurationException;
	void inactiveLoginPageByUserId(Connection con, int userId, int pageId)throws ConfigurationException;
	List<RideSeekerDTO> fetchRideSeekerUnApproved(Connection con)throws ConfigurationException;
	Map<Integer, List<RideSeekerDTO>> getunAssignedTaxi(Connection con)throws ConfigurationException;
	Map<Integer, List<RideSeekerDTO>> getunAssignedTaxiForOwner(Connection con)throws ConfigurationException;
	void makePopupMessageRead(Connection con, int[] messageId) throws ConfigurationException;
	List<FrequencyDTO> fetchFrequencyListForRideSeeker(Connection con, String rideSeekerID) throws ConfigurationException;
	List<FrequencyDTO> fetchFrequencyListForRideManager(Connection con, String rideID) throws ConfigurationException;
	List<RideSeekerDTO> fetchRecurringRideList(Connection con) throws ConfigurationException;
	void addSubSeekers(Connection con, RideSeekerDTO ride) throws ConfigurationException;
	RideSeekerDTO cancelSubSeekers(Connection con, RideSeekerDTO rideSeekerDTO) throws ConfigurationException;
	void updateRideIdDropTake(Connection con, RideManagementDTO rideToDrop, RideManagementDTO rideToTake)throws ConfigurationException;
	boolean testSingleCircleMember(Connection con, CircleMemberDTO circleMemberDTO, String id, String ctype, String ttype) throws ConfigurationException;
	List<PaymentDTO> fetchPaymentList(Connection con, int id) throws ConfigurationException;
	List<PaymentDTO> fetchActivePaymentListForCircle(Connection con, Integer[] circleIds) throws ConfigurationException;
	List<CircleOwnerManagerDTO> loadallCorpCircleForLoginUser(Connection con,String userID)throws ConfigurationException;
	List<CircleOwnerManagerDTO> loadallTaxiCircleForLoginUser(Connection con,String userID)throws ConfigurationException;
	List<PaymentPlanDTO> fetchPaymentPlanForLoginUser(Connection con,String userID)throws ConfigurationException;
	PaymentRequestDTO addPaymentRequestEntry(Connection con, PaymentRequestDTO dto)throws ConfigurationException;
	void updatePaymentRequestEntryStatusById(Connection con, PaymentRequestDTO dto) throws ConfigurationException;
	void updatePaymentRequestEntryStatusByOrderId(Connection con, PaymentRequestDTO dto) throws ConfigurationException;
	PaymentRequestDTO fetchPaymentRequestByOrderId(Connection con, PaymentRequestDTO dto) throws ConfigurationException;
	PaymentTxnsDTO paymentTxnInsert(Connection con, PaymentTxnsDTO paymentTxnsDTO)throws ConfigurationException;
	PaymentTxnsDTO paymentTxnCancel(Connection con, PaymentTxnsDTO dto)throws ConfigurationException;
	List<PaymentTxnsDTO> fetchAllTxnByDate(Connection con, Date date)throws ConfigurationException;
	void paymentTxnHoponToUser(Connection con, HoponAccountDTO hoponAccountDto, UserRegistrationDTO userDto)throws ConfigurationException;
	void paymentTxnUserToHopon(Connection con, HoponAccountDTO hoponAccountDto, UserRegistrationDTO userDto)throws ConfigurationException;
	UserRegistrationDTO updateTotalCredit(Connection con,UserRegistrationDTO userRegistrationDTO)throws ConfigurationException;
	//for ContactUs Method
	void contactUs(Connection con, ContactusDTO dto) throws ConfigurationException;
	List<SummaryMessageDTO>  loadRideSummaryMessage() throws ConfigurationException;
/*<!--  Code Changed by Kirty for selection Ride option with different User Id-->	*/
	CircleDTO getCircleType(Connection con,
			int userId) throws ConfigurationException;
/*<!--  Code Changed by Kirty for selection Ride option with different User Id-->	*/
	List<CombineRideDTO> getAllTodaysCombineVehicleList(Connection con,
			String fromAddress, String toAddress, String rideDate, int circleID)throws ConfigurationException;
	
	public abstract List<PaymentTxnsDTO> searchCompletedTransaction(
			Connection paramConnection, String paramString,
			Date paramDate1, Date paramDate2)
			throws ConfigurationException;

	public abstract List<PaymentRequestDTO> searchPaymentTransfer(
			Connection paramConnection, String paramString1,
			String paramString2, Date paramDate1, Date paramDate2,
			double paramDouble1, double paramDouble2, String paramString3)
			throws ConfigurationException;

	public void fetchAllPreviousDayRides(Connection paramConnection)
			throws ConfigurationException;
	
}


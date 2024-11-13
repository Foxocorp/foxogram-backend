package su.foxogram.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import su.foxogram.dtos.response.OkDTO;
import su.foxogram.models.*;
import su.foxogram.enums.APIEnum;
import su.foxogram.exceptions.*;
import su.foxogram.dtos.request.ChannelCreateDTO;
import su.foxogram.services.AuthenticationService;
import su.foxogram.services.ChannelsService;

@RestController
@RequestMapping(value = APIEnum.CHANNELS, produces = "application/json")
public class ChannelsController {

	private final ChannelsService channelsService;
    final Logger logger = LoggerFactory.getLogger(ChannelsController.class);

	public ChannelsController(ChannelsService channelsService, AuthenticationService authenticationService) {
		this.channelsService = channelsService;
    }

	@PostMapping("/create")
	public Channel createChannel(@RequestAttribute(value = "user") User user, @RequestBody ChannelCreateDTO body, HttpServletRequest request) {
		logger.info("CHANNEL create ({}) request");

		return channelsService.createChannel(user, body.getType(), body.getName());
	}

	@GetMapping("/{id}")
	public Channel getChannel(@RequestAttribute(value = "user") User user, @PathVariable long id, HttpServletRequest request) throws ChannelNotFoundException {
		logger.info("CHANNEL info ({}) request", id);

		return channelsService.getChannel(id);
	}

	@PostMapping("/{id}/join")
	public Member joinChannel(@RequestAttribute(value = "user") User user, @PathVariable long id, HttpServletRequest request) throws ChannelNotFoundException {
		logger.info("CHANNEL join ({}) request", id);
		Channel channel = channelsService.getChannel(id);

		return channelsService.joinUser(channel, user);
	}

	@PostMapping("/{id}/leave")
	public OkDTO leaveChannel(@RequestAttribute(value = "user") User user, @PathVariable long id, HttpServletRequest request) throws ChannelNotFoundException {
		logger.info("CHANNEL leave ({}) request", id);
		Channel channel = channelsService.getChannel(id);

		channelsService.leaveUser(channel, user);
		return new OkDTO(true);
	}

	@PatchMapping("/{id}")
	public Channel editChannel(@RequestAttribute(value = "user") User user, @PathVariable long id, HttpServletRequest request) throws ChannelNotFoundException {
		logger.info("CHANNEL edit ({}) request", id);

		return channelsService.getChannel(id);
	}

	@DeleteMapping("/{id}")
	public OkDTO deleteChannel(@RequestAttribute(value = "user") User user, @PathVariable long id, HttpServletRequest request) throws ChannelNotFoundException, MissingPermissionsException {
		logger.info("CHANNEL delete ({}) request", id);
		Channel channel = channelsService.getChannel(id);

		channelsService.deleteChannel(channel, user);

		return new OkDTO(true);
	}
}

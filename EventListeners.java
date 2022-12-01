package com.Discord.Studybot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.function.IntFunction;


public class EventListeners extends ListenerAdapter {

    @Override
    public void onGenericMessageReaction(GenericMessageReactionEvent event) {
        User user =  event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumplink =  event.getJumpUrl();

        String message = user.getAsTag() + "reacted to a message with" + emoji + "in the" + channelMention + "channel!";

        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("Motivation")){
            event.getChannel().sendMessage("there is nothing outside of yourself that can ever enable you to get better, stronger, richer, quicker, or smarter - Miyamoto Musashi.").queue();



        }
    }
}

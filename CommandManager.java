package com.Discord.Studybot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        String command = event.getName();
        if (command.equals("welcome")) { //command /welcome
            //runs the /welcome command
            String userTag = event.getUser().getAsTag();
            event.reply("Welcome to the Server, ** " + userTag + "!").queue();
        } else if (command.equals("roles")) {
            // run the /roles command
            event.deferReply().queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                response += role.getAsMention() + "\n";

                event.getHook().sendMessage(response).queue();

            }
            event.getHook().sendMessage("our message").queue();

        } else if (command.equals("say")) {
            OptionMapping messageOption = event.getOption("message");
            String message = messageOption.getAsString();

            event.getChannel().sendMessage(message).queue();
            event.reply("your message was sent").queue();

        } else if (command.equals("Motivation")) {
           OptionMapping messageOption = event.getOption("It takes 1,000 days to forge the spirit and 10,000 days to polish it. - Miyamoto Musashi");
           String message = messageOption.getAsString();
           event.getChannel().sendMessage(message).queue();
           event.reply("Here is your Quote to keep you pushing").queue();

        }
    }


    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List <CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "get welcomed by the bot, "));
        commandData.add(Commands.slash("roles", "roles for the server, "));
        commandData.add(Commands.slash("Motivation", "displays quote to motivate the user"));

        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you want the bot to say", true);
        commandData.add(Commands.slash("say", "have the bot say something.").addOptions(option1));
        OptionData option2 = new OptionData(OptionType.STRING, "display quote", "shows quote to motivate user", true);
        commandData.add(Commands.slash("Motivation", "displays quote to motivate the user.").addOptions(option2));
        event.getGuild().updateCommands().addCommands(commandData).queue();



    }


    @Override
    public void onReady(ReadyEvent event) {
        List <CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("Motivation", "displays quote to motivate the user"));
        event.getJDA().updateCommands().addCommands(commandData).queue();

    }
}

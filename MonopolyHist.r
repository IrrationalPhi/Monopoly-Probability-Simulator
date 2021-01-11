frequencies <- read.table("results.txt", sep = ",")
freq <- frequencies[,-41]
freq <- as.numeric(freq)
barplot(freq)
# axis(side = 1, at = seq_along(freq) - 0.5, tick = FALSE, labels = c(1:40))
# at_tick <- seq_len(length(count) + 1)

package dev.centremetre.paydaylog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a heist in a game that can be played.
 *
 * Different from a playing of a heist; see {@link CompletedHeist}.
 */
@Entity
@Table(name = "heists")
public class Heist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

/**
 * Order of heist list in-game:
 * No Rest For The Wicked - 1
 * Road Rage - 2
 * Dirty Ice - 3
 * Rock The Cradle - 4
 * Under The Surphaze - 5
 * Gold & Sharke - 6
 * 99 Boxes - 7
 * Touch The Sky - 8
 * Turbid Station - Cook Off+1 - 30/11/2026 (November 30 2023) - paydaythegame.com/news/payday3/2023/11/update-1-0-2
 * Cook Off - Turbid Station-1 - 30/11/2026 (November 30 2023) - paydaythegame.com/news/payday3/2023/11/update-1-0-2
 * Diamond District - 19/07/2024 (19 Aug, 2024) - Along side houston breakout steampowered.com/news/app/1272080/view/6649088134120953286
 * First World Bank - 10/12/2024 - https://store.steampowered.com/news/app/1272080/view/499433080620057409
 * Party Powder - 26/03/2025 (26 May, 2025)
 * Delivery Charge - 10/09/2025 (10 Sep, 2025)
 * Shopping Spree - 13/01/2026 (13 Jan, 2026)
 * Syntax Error - 12/12/2023 (12 Dec, 2023)
 * Boys In Blue - 27/06/2024 (27 Jun, 2024)
 * Houston Breakout - 19/07/2024 (19 Aug, 2024)
 * Fear & Greed - 16/09/2024 (19 Aug, 2024)
 * Bank Withdrawal - 25/06/2025 (June 25th, 2025) - starbreeze.com/news/payday-3-smash-grab-update-launches-today
 * Search And Seizure - 25/06/2025 (June 25th, 2025) - starbreeze.com/news/payday-3-smash-grab-update-launches-today
 *
 * No Rest For The Wicked - 1
 * Road Rage - 2
 * Dirty Ice - 3
 * Rock The Cradle - 4
 * Under The Surphaze - 5
 * Gold & Sharke - 6
 * 99 Boxes - 7
 * Touch The Sky - 8
 * Cook Off - 9
 * Turbid Station - 10
 * Syntax Error - 11
 * Boys In Blue - 12
 * Houston Breakout - 13
 * Diamond District - 14
 * Fear & Greed - 15
 * First World Bank - 16
 * Party Powder - 17
 * Bank Withdrawal - 18
 * Search And Seizure - 19
 * Delivery Charge - 20
 * Shopping Spree - 21
 *
 *
 * Cook Off - 30/11/2023 - paydaythegame.com/news/payday3/2023/11/update-1-0-2
 *
 * Turbid Station – 30/11/2023 – paydaythegame.com/news/payday3/2023/11/update-1-0-2
 *
 * Syntax Error – 12/12/2023
 *
 * Boys In Blue – 27/06/2024
 *
 * Diamond District – 19/08/2024 – steampowered.com/news/app/1272080/view/6649088134120953286
 *
 * Houston Breakout – 19/08/2024
 *
 * Fear & Greed – 16/09/2024
 *
 * First World Bank – 10/12/2024 – steampowered.com/news/app/1272080/view/499433080620057409
 *
 * Party Powder – 26/03/2025
 *
 * Bank Withdrawal – 25/06/2025 – starbreeze.com/news/payday-3-smash-grab-update-launches-today
 *
 * Search And Seizure – 25/06/2025 – starbreeze.com/news/payday-3-smash-grab-update-launches-today
 *
 * Delivery Charge – 10/09/2025
 *
 * Shopping Spree – 13/01/2026
 */



import React from 'react';
import { NavLink } from 'react-router-dom';
import { TarotCardIcon } from '../components/CustomIcons';
import { CategoryScroll } from '../components/CategoryScroll';
import { SpasmodicText } from '../components/SpasmodicText';

export const Home: React.FC = () => {
  return (
    <div className="space-y-12 pb-8 animate-in fade-in slide-in-from-bottom-4 duration-1000 perspective-1000">

      {/* Header Photo */}
      <div className="w-full h-48 sm:h-64 md:h-80 overflow-hidden relative border-b-2 border-[var(--accent-p)] shadow-[0_0_20px_var(--accent-p)] group transform-style-3d">
        <img
            src="https://images.unsplash.com/photo-1543501799-a3c306d28dd9?q=80&w=2000&auto=format&fit=crop"
            alt="Neon Balcony"
            className="w-full h-full object-cover object-top opacity-80 mix-blend-luminosity hover:mix-blend-normal transition-all duration-[1500ms] pixel-image group-hover:scale-125 origin-center animate-breathe"
        />
        <div className="absolute inset-0 bg-gradient-to-t from-[var(--bg-color)] to-transparent pointer-events-none"></div>
        <div className="absolute bottom-4 left-4 sm:bottom-8 sm:left-8 translate-z-20 group-hover:translate-z-30 transition-transform duration-[1500ms]">
             <h2 className="text-4xl sm:text-5xl md:text-6xl font-glitch leading-none text-white uppercase glitch-text drop-shadow-[0_0_15px_var(--accent-c)] animate-float">
                <SpasmodicText text="THE MAJOR" /> <br/>
                <span className="text-[var(--accent-c)] animate-pulse inline-block">ARCANA</span>
            </h2>
        </div>
      </div>

      <div className="grid grid-cols-1 gap-8 items-start px-2 sm:px-4 lg:px-8">

        {/* Featured Actions Grid */}
        <div className="grid grid-cols-2 gap-4 sm:gap-6 lg:gap-8">
            {/* Shuffle CTA */}
            <NavLink to="/random" className="group relative block w-full bg-[var(--card-bg)] border border-[var(--accent-c)] backdrop-blur-sm overflow-hidden transition-all hover:border-[var(--accent-p)] hover:scale-[1.02] aspect-square flex flex-col items-center justify-center p-4 text-center">
                <div className="absolute inset-0 bg-gradient-to-br from-black/80 to-transparent z-10"></div>
                <div className="relative z-20 flex flex-col items-center gap-3">
                    <TarotCardIcon className="text-[var(--accent-c)] group-hover:text-[var(--accent-p)] pixel-icon transition-colors" size={32} />
                    <h3 className="font-glitch text-lg tracking-widest text-white uppercase group-hover:drop-shadow-[0_0_8px_var(--accent-p)] leading-none">SHUFFLE<br/>FATE</h3>
                </div>
            </NavLink>

            {/* Crystal Ball CTA */}
            <NavLink to="/crystal-ball" className="group relative block w-full bg-[var(--card-bg)] border border-[var(--accent-p)] backdrop-blur-sm overflow-hidden transition-all hover:border-[var(--accent-c)] hover:scale-[1.02] aspect-square flex flex-col items-center justify-center p-4 text-center">
                 <div className="absolute inset-0 bg-gradient-to-bl from-black/80 to-transparent z-10"></div>
                 {/* CSS Crystal Ball Simulation */}
                 <div className="w-12 h-12 rounded-full bg-gradient-to-br from-[var(--bg-teal)] to-black border border-[var(--accent-c)] relative z-20 shadow-[0_0_15px_var(--accent-c)] mb-3 group-hover:animate-pulse">
                    <div className="absolute inset-0 bg-white/10 rounded-full animate-ping opacity-20"></div>
                 </div>
                 <h3 className="relative z-20 font-glitch text-lg tracking-widest text-white uppercase group-hover:text-[var(--accent-c)] leading-none">CRYSTAL<br/>BALL</h3>
            </NavLink>
        </div>

        {/* Categories Scroll */}
        <div className="space-y-4">
          <h4 className="font-mono text-[11px] text-[var(--accent-p)] uppercase tracking-[0.3em] border-b border-white/20 pb-3 font-bold">SELECT YOUR CARD</h4>
          <CategoryScroll />
        </div>
      </div>
    </div>
  );
};

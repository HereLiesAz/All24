
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { Arcana } from '../types';
import { ARCANA_SHORT_DESCRIPTIONS, LOCATIONS } from '../constants';

const ARCANA_IMAGE_MAP = LOCATIONS.reduce((acc, loc) => {
    if (!acc[loc.arcana]) {
        acc[loc.arcana] = loc.image;
    }
    return acc;
}, {} as Record<string, string>);

const getCategoryImage = (arcana: Arcana) => {
    return ARCANA_IMAGE_MAP[arcana] || "https://images.unsplash.com/photo-1543501799-a3c306d28dd9?q=80&w=600&auto=format&fit=crop";
}

export const CategoryScroll: React.FC = () => {
    const [activeIndex, setActiveIndex] = useState<number | null>(null);

    const categories = Object.keys(ARCANA_SHORT_DESCRIPTIONS) as Arcana[];

    return (
        <div className="flex flex-col gap-8 py-4 px-2">
            {categories.map((arcana, index) => {
                const isActive = activeIndex === index;
                const shortDesc = ARCANA_SHORT_DESCRIPTIONS[arcana];
                const image = getCategoryImage(arcana);

                return (
                    <div
                        key={arcana}
                        onClick={() => setActiveIndex(isActive ? null : index)}
                        className={`group relative overflow-hidden rounded-2xl h-[60vh] transition-all duration-[400ms] ease-out will-change-transform cursor-pointer border border-white/10 ${isActive ? 'scale-[1.02] z-10 border-[var(--accent-c)] shadow-[0_0_20px_rgba(45,212,191,0.3)]' : 'scale-100 hover:border-white/30 active:scale-[0.98]'}`}
                    >
                        <img
                            src={image}
                            alt={arcana}
                            className={`w-full h-full object-cover block transition-all duration-700 pixel-image ${isActive ? 'brightness-110 saturate-150 scale-110' : 'brightness-50 grayscale-[0.3]'}`}
                        />

                        {/* Static Overlay for Category Name when not active */}
                        {!isActive && (
                             <div className="absolute inset-0 bg-gradient-to-t from-black/90 via-black/20 to-transparent flex flex-col justify-end p-8">
                                <h3 className="text-white text-3xl font-glitch uppercase tracking-[0.2em] glitch-text leading-tight">{arcana}</h3>
                                <div className="h-0.5 w-0 group-hover:w-16 bg-[var(--accent-c)] transition-all duration-500"></div>
                             </div>
                        )}

                        {/* Active Detail Overlay */}
                        <div className={`absolute inset-0 flex items-end p-8 bg-gradient-to-t from-black/95 via-black/50 to-transparent transition-all duration-500 ${isActive ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-10 pointer-events-none'}`}>
                            <div className="flex flex-col gap-3 w-full">
                                <h3 className="text-[var(--accent-c)] text-4xl font-glitch uppercase tracking-[0.1em] drop-shadow-[0_0_15px_rgba(45,212,191,0.5)] leading-tight">{arcana}</h3>
                                <div className="h-px w-24 bg-[var(--accent-c)] mb-2"></div>
                                <p className="text-white font-mono text-sm mb-8 leading-relaxed italic border-l-2 border-[var(--accent-p)] pl-4 max-w-[80%]">
                                    {shortDesc}
                                </p>
                                <NavLink
                                    to={`/category/${encodeURIComponent(arcana)}`}
                                    onClick={(e) => e.stopPropagation()}
                                    className="inline-block w-fit px-8 py-4 bg-black/80 backdrop-blur-md border border-[var(--accent-c)] text-[var(--accent-c)] font-mono text-xs uppercase tracking-[0.3em] hover:bg-[var(--accent-c)] hover:text-black transition-all font-bold shadow-[6px_6px_0px_var(--accent-p)] active:translate-x-[2px] active:translate-y-[2px] active:shadow-none"
                                >
                                    OPEN THE DECK
                                </NavLink>
                            </div>
                        </div>
                    </div>
                );
            })}
        </div>
    );
};
